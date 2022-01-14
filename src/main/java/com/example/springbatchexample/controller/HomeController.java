package com.example.springbatchexample.controller;

import com.example.springbatchexample.entity.UserData;
import com.example.springbatchexample.repo.UserDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;


    @GetMapping("/load")
    public void seedDatabase() {
        LOGGER.info("Loading data starting");
        UserData user1 = new UserData("pranshu.patel", "Pranshu Patel", "F");
        UserData user2 = new UserData("john.doe", "John Doe", "P");
        UserData user3 = new UserData("chris.martin", "Chris Martin", "P");

        userDataRepository.saveAll(Arrays.asList(user1, user2, user3));
        LOGGER.info("data loaded");
    }

    @GetMapping("/batch")
    public void batchJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException,
            JobRestartException, JobInstanceAlreadyCompleteException {

        LOGGER.info("batch job starting");
        jobLauncher.run(job, newExecution());
    }

    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }
}
