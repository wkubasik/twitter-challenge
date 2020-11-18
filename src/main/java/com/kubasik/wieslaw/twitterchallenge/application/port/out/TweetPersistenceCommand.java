package com.kubasik.wieslaw.twitterchallenge.application.port.out;

import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

public interface TweetPersistenceCommand {
    Tweet postTweet(Tweet tweet);
}
