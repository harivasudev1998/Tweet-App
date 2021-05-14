package com.tweetapp.app.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "userTweets")
public class Tweets implements Comparable {

    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @Field(name = "tweet")
    private String tweet;

    @Field(name = "createdBy")
    TweetUserObject tweetUserObject;

    @Field(name = "likes")
    Likes likes;

    @Field(name = "timeOfPost")
    private LocalDateTime timeOfPost;

    @Field(name = "tags")
    private List<String> tags;

    @Field(name = "replies")
    private List<TweetReply> replies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public LocalDateTime getTimeOfPost() {
        return timeOfPost;
    }

    public void setTimeOfPost(LocalDateTime timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<TweetReply> getReplies() {
        return replies;
    }

    public void setReplies(List<TweetReply> replies) {
        this.replies = replies;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public TweetUserObject getTweetUserObject() {
        return tweetUserObject;
    }

    public void setTweetUserObject(TweetUserObject tweetUserObject) {
        this.tweetUserObject = tweetUserObject;
    }

    @Override
    public int compareTo(Object o) {
        Tweets tweet = (Tweets) o;
        return tweet.getTimeOfPost().compareTo(getTimeOfPost());
    }
}
