package com.tweetapp.app.demo.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweetapp.app.demo.domain.Tweets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Service
public class TweetEventProducer {
    private static final Logger logger = LoggerFactory.getLogger(TweetEventProducer.class);

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    public void sendTweetEvents(Tweets tweet) throws JsonProcessingException {
        String key = tweet.getId();
        String value = objectMapper.writeValueAsString(tweet);
        ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.sendDefault(key, value);
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                handleFailure(key, value, throwable);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                handleSuccess(key, value, result);
            }
        });
    }

    public SendResult<String, String> sendTweetEventsSynchrous(Tweets tweet) throws JsonProcessingException {
        String key = tweet.getId();
        String value = objectMapper.writeValueAsString(tweet);
        SendResult<String, String> result = null;

        try {
            result = kafkaTemplate.sendDefault(key, value).get();
        } catch (ExecutionException | InterruptedException e) {
            logger.error("Message sent for ExecutionException|InterruptedException key: {} and value : {}", key, value);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Message sent Exception for key: {} and value : {}", key, value);
            e.printStackTrace();
        }
        return result;
    }

    private void handleSuccess(String key, String value, SendResult<String, String> result) {
        logger.debug("Message sent for key: {} and value : {}", key, value);
    }

    private void handleFailure(String key, String value, Throwable throwable) {
        logger.error("Message sent for key: {} and value : {}", key, value);
        try {
            throw throwable;
        } catch (Throwable e) {
            logger.error("Error i failure :{}", throwable.getMessage());
        }
    }
}
