package com.tweetx.search.core.api;

/**
 * Authored by dushan.p@viewqwest.com on 14/7/19.
 * http://dushan.lk
 */
public class HashTag {

    private String text;

    private int count;

    public HashTag() {
    }

    public HashTag(String text, int count) {
        this.text = text;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "text='" + text + '\'' +
                ", count=" + count +
                '}';
    }
}
