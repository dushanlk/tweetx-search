package com.tweetx.search.core.api;

import java.util.List;

/**
 * Authored by dushan.p@viewqwest.com on 13/7/19.
 * http://dushan.lk
 */
public class SearchResponse {

    private int status;

    private String description;

    private List<Tweet> tweets;

    private List<HashTag> topHashTags;

    public SearchResponse() {
    }

    public SearchResponse(int status, String description, List<Tweet> tweets, List<HashTag> topHashTags) {
        this.status = status;
        this.description = description;
        this.tweets = tweets;
        this.topHashTags = topHashTags;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<HashTag> getTopHashTags() {
        return topHashTags;
    }

    public void setTopHashTags(List<HashTag> topHashTags) {
        this.topHashTags = topHashTags;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                ", tweets=" + tweets +
                ", topHashTags=" + topHashTags +
                '}';
    }
}
