package com.kubasik.wieslaw.twitterchallenge.application.service;

import com.kubasik.wieslaw.twitterchallenge.annotations.UseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.GetTweetsUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceQuery;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetTweetsUseCaseService implements GetTweetsUseCase {

    private final TweetPersistenceQuery tweetPersistenceQuery;

    @Override
    public List<Tweet> getTweets(String username) {
        return tweetPersistenceQuery.getTweetsByUsernameOrdered(username);
    }
}
