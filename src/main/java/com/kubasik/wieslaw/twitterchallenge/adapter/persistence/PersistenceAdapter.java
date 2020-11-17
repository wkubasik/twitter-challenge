package com.kubasik.wieslaw.twitterchallenge.adapter.persistence;

import com.kubasik.wieslaw.twitterchallenge.annotations.Adapter;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.FolloweesPersistenceCommand;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceCommand;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceQuery;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;
import com.kubasik.wieslaw.twitterchallenge.exceptions.UsernameNotFoundException;
import com.kubasik.wieslaw.twitterchallenge.utils.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Adapter
public class PersistenceAdapter implements TweetPersistenceCommand, TweetPersistenceQuery, FolloweesPersistenceCommand {

    private final Map<String, List<Tweet>> userTweets = new ConcurrentHashMap<>();
    private final Map<String, List<String>> userFollowees = new ConcurrentHashMap<>();

    @Override
    public Tweet postTweetByUsername(String username, Tweet tweet) {
        userTweets.merge(username, Collections.singletonList(tweet), CollectionUtils::concatenateListsAndSort);
        return tweet;
    }

    @Override
    public void follow(String username, String usernameToFollow) {
        checkIfUsernameExists(username);
        checkIfUsernameExists(usernameToFollow);
        userFollowees.merge(username, Collections.singletonList(usernameToFollow), CollectionUtils::concatenateListsAndSort);
    }

    @Override
    public List<Tweet> getTweetsByUsernameOrdered(String username) {
        checkIfUsernameExists(username);
        return userTweets.get(username);
    }

    @Override
    public List<Tweet> getFolloweesTweetsByUsernameOrdered(String username) {
        checkIfUsernameExists(username);
        return userFollowees.get(username).stream()
                .flatMap(followee -> userTweets.getOrDefault(followee, Collections.emptyList()).stream())
                .sorted()
                .collect(Collectors.toList());
    }

    private void checkIfUsernameExists(String username) {
        if (!userTweets.containsKey(username)) {
            throw new UsernameNotFoundException(username);
        }
    }
}
