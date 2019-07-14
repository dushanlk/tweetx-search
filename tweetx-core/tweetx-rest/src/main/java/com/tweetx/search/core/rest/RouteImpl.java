package com.tweetx.search.core.rest;

import com.tweetx.search.core.service.TweetSearch;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Authored by dushan.p@viewqwest.com on 11/7/19.
 * http://dushan.lk
 */
@Component(immediate = true)
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class RouteImpl implements Route {

    private static Logger LOGGER = LoggerFactory.getLogger(RouteImpl.class);

    private TweetSearch search;

    @GET
    @Path("/search/{keyword}")
    public Response search(@PathParam("keyword") String keyword) {
        LOGGER.debug("Received search request [{}]", keyword);
        return search.search(keyword);
    }

    @Reference
    public void setSearch(TweetSearch search) {
        this.search = search;
    }
}
