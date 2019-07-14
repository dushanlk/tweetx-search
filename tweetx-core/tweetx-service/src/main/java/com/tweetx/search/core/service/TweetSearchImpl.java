package com.tweetx.search.core.service;

import com.tweetx.search.core.api.HashTagCounter;
import com.tweetx.search.core.api.SearchResponse;
import com.tweetx.search.core.api.Tweet;
import com.tweetx.search.core.configuration.ConfigurationService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import static com.tweetx.search.core.configuration.ConfigConstants.*;

/**
 * Authored by dushan.p@viewqwest.com on 13/7/19.
 * http://dushan.lk
 */
@Component(immediate = true)
public class TweetSearchImpl implements TweetSearch {

    private static final Logger LOGGER = LoggerFactory.getLogger(TweetSearchImpl.class);

    private ConfigurationService configurationService;

    @Override
    public Response search(String keyword) {

        final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder()
                .setDebugEnabled(true)
                .setOAuthConsumerKey(configurationService.get(AUTH_CONSUMER_KEY))
                .setOAuthConsumerSecret(configurationService.get(AUTH_CONSUMER_SECRET))
                .setOAuthAccessToken(configurationService.get(AUTH_ACCESS_TOKEN))
                .setOAuthAccessTokenSecret(configurationService.get(AUTH_ACCESS_TOKEN_SECRET));

        final TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        final Twitter twitter = twitterFactory.getInstance();

        /*
         * Top 100 results.
         * Top 10 hash tags.
         * Number of times for hash tags.
         * */
        try {
            final QueryResult result = twitter.search(new Query(keyword).count(100));
            final List<Tweet> tweets = result.getTweets().stream().map(Tweet::build).collect(Collectors.toList());

            final SearchResponse response = new SearchResponse();
            response.setTweets(tweets);
            response.setTopHashTags(HashTagCounter.build().getTopHashTags(tweets));

            Response.ResponseBuilder rspBuilder = Response.ok(response);
            rspBuilder.header("Access-Control-Allow-Origin", "*");

            return rspBuilder.build();
        } catch (TwitterException e) {
            LOGGER.error("Error occurred", e);
            return Response.status(e.getStatusCode(), e.getErrorMessage()).build();
        }
    }

    @Reference
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
