package com.example.springbatchexample.config;

import com.example.springbatchexample.entity.ReqData;
import com.example.springbatchexample.entity.UserData;
import com.example.springbatchexample.items.UserDataProcessor;
import com.example.springbatchexample.items.UserDataWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private UserDataProcessor userDataProcessor;
    private UserDataWriter userDataWriter;
    private final DataSource dataSource;

    @Autowired
    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                       UserDataProcessor userDataProcessor, UserDataWriter userDataWriter,
                       DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.userDataProcessor = userDataProcessor;
        this.userDataWriter = userDataWriter;
        this.dataSource = dataSource;
    }

    @Bean
    public Job importJob(Step step1) {
        LOGGER.info("inside job module");
        return jobBuilderFactory.get("import-job").incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step createStep() {
        LOGGER.info("inside step module");
        return stepBuilderFactory.get("step1")
                .<UserData, ReqData>chunk(10)
                .reader(itemReader())
                .processor(userDataProcessor)
                .writer(userDataWriter)
                .build();
    }

    private static final String QUERY_FIND_USERS =
            "SELECT " +
                    "user_id, " +
                    "name, " +
                    "status " +
                    "FROM USER_DATA " +
                    "WHERE status = 'P'";

    @Bean
    public ItemReader<UserData> itemReader() {
        return new JdbcCursorItemReaderBuilder<UserData>()
                .name("cursorItemReader")
                .dataSource(dataSource)
                .sql(QUERY_FIND_USERS)
                .rowMapper(new BeanPropertyRowMapper<>(UserData.class))
                .build();
    }

}
