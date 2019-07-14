package com.tweetx.search.core.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Authored by dushan.p@viewqwest.com on 14/7/19.
 * http://dushan.lk
 */
public class HashTagCounter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HashTagCounter.class);

    private Map<String, Integer> tags = new HashMap<>();

    public List<HashTag> getTopHashTags(List<Tweet> tweets) {
        tweets.forEach(tweet -> tweet.getHashTags().forEach(hashTag -> {
            if (tags.containsKey(hashTag.toLowerCase())) {
                final Integer currentCount = tags.get(hashTag.toLowerCase());
                tags.put(hashTag.toLowerCase(), currentCount + 1);
            } else {
                tags.put(hashTag.toLowerCase(), 1);
            }
        }));

        final List<HashTag> topHashTags = tags.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .map(entry -> new HashTag(entry.getKey(), entry.getValue()))
                .limit(10)
                .collect(Collectors.toList());
        LOGGER.debug("Generated top 10 hash tags [{}]", topHashTags);
        return topHashTags;
    }

    public static HashTagCounter build() {
        return new HashTagCounter();
    }
}
