package com.kubasik.wieslaw.twitterchallenge.application.port.in;

import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

public interface PostTweetUseCase {
    Tweet postTweet(String username, String message);
}
