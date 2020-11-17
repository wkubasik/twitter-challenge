package com.kubasik.wieslaw.twitterchallenge.application.port.in;

import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

import java.util.List;

public interface GetFolloweesTweetsUseCase {
    List<Tweet> getFolloweesTweets(String username);
}
