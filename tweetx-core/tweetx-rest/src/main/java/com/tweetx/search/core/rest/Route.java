package com.tweetx.search.core.rest;

import javax.ws.rs.core.Response;

/**
 * Authored by dushan.p@viewqwest.com on 11/7/19.
 * http://dushan.lk
 */
public interface Route {

    Response search(String keyword);
}
