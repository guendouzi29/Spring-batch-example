package com.example.springbatchexample.items;

import com.example.springbatchexample.entity.ReqData;
import com.example.springbatchexample.repo.ReqDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataWriter implements ItemWriter<ReqData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataWriter.class);

    @Autowired
    ReqDataRepository reqDataRepository;

    @Override
    public void write(List<? extends ReqData> list) throws Exception {
        LOGGER.info("inside writer");
        reqDataRepository.saveAll(list);
    }
}
