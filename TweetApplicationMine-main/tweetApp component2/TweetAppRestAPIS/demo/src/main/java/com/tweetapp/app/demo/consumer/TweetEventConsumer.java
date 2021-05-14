package com.tweetapp.app.demo.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TweetEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(TweetEventConsumer.class);

    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = "Tweet-App-events",groupId = "group_id")
    public void consumeMssage(String message) throws JsonProcessingException {
//        String message = objectMapper.writeValueAsString(tweet);
        logger.info(String.format("#### -> Consumed Message ->%s", message));
    }
}
