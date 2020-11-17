package com.kubasik.wieslaw.twitterchallenge.application.service;

import com.kubasik.wieslaw.twitterchallenge.annotations.UseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.GetFolloweesTweetsUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceQuery;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetFolloweesTweetsUseCaseService implements GetFolloweesTweetsUseCase {

    private final TweetPersistenceQuery tweetPersistenceQuery;

    @Override
    public List<Tweet> getFolloweesTweets(String username) {
        return tweetPersistenceQuery.getFolloweesTweetsByUsernameOrdered(username);
    }
}
