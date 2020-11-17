package com.kubasik.wieslaw.twitterchallenge.application.port.in;

import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

import java.util.List;

public interface GetTweetsUseCase {
    List<Tweet> getTweets(String username);
}
