package com.example.springbatchexample.items;

import com.example.springbatchexample.entity.ReqData;
import com.example.springbatchexample.entity.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserDataProcessor implements ItemProcessor<UserData, ReqData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDataProcessor.class);

    @Override
    public ReqData process(UserData userData) throws Exception {
        LOGGER.info("inside processor");
        return new ReqData(userData.getUserId(), userData.getName());
    }
}
