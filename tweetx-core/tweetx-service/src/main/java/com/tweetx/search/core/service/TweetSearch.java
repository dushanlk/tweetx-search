package com.tweetx.search.core.service;

import javax.ws.rs.core.Response;

/**
 * Authored by dushan.p@viewqwest.com on 13/7/19.
 * http://dushan.lk
 */
public interface TweetSearch {

    Response search(String keyword);
}
