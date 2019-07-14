package com.tweetx.search.core.api;

import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Authored by dushan.p@viewqwest.com on 13/7/19.
 * http://dushan.lk
 */
public class Tweet {

    private long id;

    private String createdDateTime;

    private String text;

    private List<String> hashTags;

    private String user;

    private String userProfileImage;

    public Tweet() {
    }

    public Tweet(long id, String createdDateTime, String text, List<String> hashTags, String user, String userProfileImage) {
        this.id = id;
        this.createdDateTime = createdDateTime;
        this.text = text;
        this.hashTags = hashTags;
        this.user = user;
        this.userProfileImage = userProfileImage;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getHashTags() {
        return hashTags;
    }

    public void setHashTags(List<String> hashTags) {
        this.hashTags = hashTags;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public static Tweet build(Status status) {
        return new Tweet(
                status.getId(),
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(status.getCreatedAt()),
                status.getRetweetedStatus() != null ? status.getRetweetedStatus().getText() : status.getText(),
                Arrays.stream(status.getHashtagEntities()).map(HashtagEntity::getText).collect(Collectors.toList()),
                status.getUser().getScreenName(),
                status.getUser().getProfileImageURL());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", text='" + text + '\'' +
                ", hashTags=" + hashTags +
                ", user='" + user + '\'' +
                ", userProfileImage='" + userProfileImage + '\'' +
                '}';
    }
}
