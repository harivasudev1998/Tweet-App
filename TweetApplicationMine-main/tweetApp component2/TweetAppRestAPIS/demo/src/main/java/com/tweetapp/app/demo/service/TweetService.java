package com.tweetapp.app.demo.service;

import com.tweetapp.app.demo.domain.Likes;
import com.tweetapp.app.demo.domain.TweetReply;
import com.tweetapp.app.demo.domain.Tweets;
import com.tweetapp.app.demo.exception.DBOPerationException;
import com.tweetapp.app.demo.repository.TweetsRepository;
import com.tweetapp.app.demo.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TweetService {

    @Autowired
    TweetsRepository tweetsRepository;

    public Tweets postNewTweet(Tweets tweets) {
        try {
            tweets.setTimeOfPost(LocalDateTime.now());
            Tweets tweet = tweetsRepository.save(tweets);
            if (tweet != null) {
                return tweet;
            }
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public Tweets editTweet(Tweets editedTweet) {
        try {
            Tweets tweet = tweetsRepository.findById(editedTweet.getId()).get();
            tweet.setTweet(editedTweet.getTweet());
            if (editedTweet.getTags() != null) {
                tweet.setTags(editedTweet.getTags());
            }
            tweet.setTimeOfPost(LocalDateTime.now());
            tweet = tweetsRepository.save(tweet);
            if (tweet != null) {
                return tweet;
            }
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public Tweets addReplyToTweet(String tweetId, TweetReply tweetReply) {
        try {
            Tweets tweet = tweetsRepository.findById(tweetId).get();
            List<TweetReply> tweetReplies = new ArrayList<>();
            tweetReply.setTimeOfPost(LocalDateTime.now());
            if(tweet.getReplies() != null) {
                tweetReplies = tweet.getReplies();
                tweetReplies.add(tweetReply);
                Collections.sort(tweetReplies);
            } else {
                tweetReplies.add(tweetReply);
            }
            tweet.setReplies(tweetReplies);
            tweet = tweetsRepository.save(tweet);
            if (tweet != null) {
                return tweet;
            }
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public Tweets likeToTweet(String editedTweetId, Likes likes) {
        try {
            Tweets tweet = tweetsRepository.findById(editedTweetId).get();
            Likes likes1 = new Likes();
            List<String> likedByUser = new ArrayList<>();
            if (tweet.getLikes() != null) {
                likes1.setCount(tweet.getLikes().getCount() + likes.getCount());
                likedByUser = tweet.getLikes().getLikedBy();
            } else {
                likes1.setCount(likes.getCount());
            }

            if (likes.getCount() > 0) {
                likedByUser.add(likes.getLikedBy().get(0));
            } else {
                likedByUser.remove(likes.getLikedBy().get(0));
            }
            likes1.setLikedBy(likedByUser);
            tweet.setLikes(likes1);
            tweet = tweetsRepository.save(tweet);
            if (tweet != null) {
                return tweet;
            }
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
        return null;
    }

    public List<Tweets> getAllTweetOfUser(String loginID) {
        try {
            List<Tweets> tweetList = tweetsRepository.findByCreatedBy(loginID);
            return tweetList;
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
    }

    public List<Tweets> getAllTweets() {
        try {
            List<Tweets> tweetList = tweetsRepository.findAll();
            Collections.sort(tweetList);
            return tweetList;
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
    }

    public boolean deleteTweetOfUser(String tweetId) {
        try {
            tweetsRepository.deleteById(tweetId);
            return true;
        } catch (Exception exception) {
            throw new DBOPerationException(Constants.DBERROR);
        }
    }

}
