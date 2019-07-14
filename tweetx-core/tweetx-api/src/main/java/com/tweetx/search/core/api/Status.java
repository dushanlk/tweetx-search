package com.tweetx.search.core.api;

/**
 * Authored by dushan.p@viewqwest.com on 14/7/19.
 * http://dushan.lk
 */
public enum Status {
    SUCCESS(200, "Operation successful"),
    NO_TWEETS_FOUND(204, "No tweets found, try again with another keyword."),
    INTERNAL_SERVER_ERROR(500, "Oops! Something went wrong");

    private int code;

    private String description;

    Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
