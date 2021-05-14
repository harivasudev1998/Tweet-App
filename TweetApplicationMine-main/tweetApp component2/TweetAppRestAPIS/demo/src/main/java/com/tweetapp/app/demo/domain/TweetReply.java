package com.tweetapp.app.demo.domain;

import java.time.LocalDateTime;

public class TweetReply implements Comparable {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDateTime timeOfPost;
    private String reply;

    private String gender;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReply() {
        return reply;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getTimeOfPost() {
        return timeOfPost;
    }

    public void setTimeOfPost(LocalDateTime timeOfPost) {
        this.timeOfPost = timeOfPost;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public int compareTo(Object o) {
        TweetReply tweetReply = (TweetReply) o;
        return tweetReply.getTimeOfPost().compareTo(getTimeOfPost());
    }
}
