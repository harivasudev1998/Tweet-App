package com.tweetapp.app.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tweetapp.app.demo.domain.Likes;
import com.tweetapp.app.demo.domain.TweetReply;
import com.tweetapp.app.demo.domain.Tweets;
//import com.tweetapp.app.demo.producer.TweetEventProducer;
import com.tweetapp.app.demo.service.TweetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/tweet")
@CrossOrigin(origins = "http://localhost:4200")
public class TweetController {

    private static final Logger logger = LoggerFactory.getLogger(TweetController.class);


    @Autowired
    TweetService tweetService;

//    @Autowired
//    TweetEventProducer tweetEventProducer;

    @PostMapping("")
    public ResponseEntity<Tweets> postNewTweet(@RequestBody Tweets tweet) throws JsonProcessingException {
        Tweets tweetResponse =  tweetService.postNewTweet(tweet);
        logger.info("before send Tweet event");
//        logger.info("{}", tweetEventProducer.sendTweetEventsSynchrous(tweetResponse));
        logger.info("after send Tweet event");
        return new ResponseEntity<>(tweetResponse, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Tweets> editTweet(@RequestBody Tweets tweet) {
        return new ResponseEntity<>(tweetService.editTweet(tweet), HttpStatus.OK);
    }

    @PutMapping("/like/{tweetId}")
    public ResponseEntity<Tweets> likeToTweet(@PathVariable String tweetId, @RequestBody Likes likes) {
        return new ResponseEntity<>(tweetService.likeToTweet(tweetId, likes), HttpStatus.OK);
    }

    @PutMapping("/{tweetId}/reply")
    public ResponseEntity<Tweets> addReplyToTweet(@PathVariable String tweetId, @RequestBody TweetReply tweetReply) {
        return new ResponseEntity<>(tweetService.addReplyToTweet(tweetId, tweetReply), HttpStatus.OK);
    }

    @GetMapping("/allTweets")
    public ResponseEntity<List<Tweets>> getAllTweets() {
        return new ResponseEntity<>(tweetService.getAllTweets(), HttpStatus.OK);
    }

    @GetMapping("/allUserTweets/{loginID}")
    public ResponseEntity<List<Tweets>> getAllTweetOfUser(@PathVariable String loginID) {
        return new ResponseEntity<>(tweetService.getAllTweetOfUser(loginID), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{tweetId}")
    public ResponseEntity<Boolean> deleteTweetOfUser(@PathVariable String tweetId) {
        return new ResponseEntity<>(tweetService.deleteTweetOfUser(tweetId), HttpStatus.OK);
    }
}
