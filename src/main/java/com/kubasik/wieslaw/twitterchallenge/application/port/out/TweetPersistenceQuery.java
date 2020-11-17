package com.kubasik.wieslaw.twitterchallenge.application.port.out;

import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

import java.util.List;

public interface TweetPersistenceQuery {
    List<Tweet> getTweetsByUsernameOrdered(String username);

    List<Tweet> getFolloweesTweetsByUsernameOrdered(String username);
}
