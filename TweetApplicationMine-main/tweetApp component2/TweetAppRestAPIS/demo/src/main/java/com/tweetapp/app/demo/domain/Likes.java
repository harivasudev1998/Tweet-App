package com.tweetapp.app.demo.domain;

import java.util.List;

public class Likes {
    private int count;
    private List<String> likedBy;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<String> likedBy) {
        this.likedBy = likedBy;
    }
}
