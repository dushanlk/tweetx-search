package com.tweetx.search.core.service;

import com.tweetx.search.core.api.HashTagCounter;
import com.tweetx.search.core.api.SearchResponse;
import com.tweetx.search.core.api.Status;
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

import static com.tweetx.search.core.api.Status.INTERNAL_SERVER_ERROR;
import static com.tweetx.search.core.api.Status.NO_TWEETS_FOUND;
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
                .setOAuthAccessTokenSecret(configurationService.get(AUTH_ACCESS_TOKEN_SECRET))
                .setTweetModeExtended(true);

        final TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        final Twitter twitter = twitterFactory.getInstance();

        try {
            final QueryResult result = twitter.search(new Query(keyword).count(100));
            final List<Tweet> tweets = result.getTweets().stream().map(Tweet::build).collect(Collectors.toList());

            if (tweets.size() > 0) {
                final SearchResponse response = new SearchResponse(Status.SUCCESS, tweets, HashTagCounter.build().getTopHashTags(tweets));
                LOGGER.debug("Generated response [{}]", response);
                return response.toResponse();
            } else {
                LOGGER.debug("No tweets found for the query [{}]", keyword);
                return new SearchResponse(NO_TWEETS_FOUND).toResponse();
            }
        } catch (TwitterException e) {
            LOGGER.error("Error occurred", e);
            return new SearchResponse(INTERNAL_SERVER_ERROR).toResponse();
        }
    }

    @Reference
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }
}
