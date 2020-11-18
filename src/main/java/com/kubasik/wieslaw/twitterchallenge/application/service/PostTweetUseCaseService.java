package com.kubasik.wieslaw.twitterchallenge.application.service;

import com.kubasik.wieslaw.twitterchallenge.annotations.UseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.PostTweetUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.CurrentDateTime;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceCommand;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class PostTweetUseCaseService implements PostTweetUseCase {

    private final TweetPersistenceCommand tweetPersistenceCommand;
    private final CurrentDateTime currentDateTime;

    @Override
    public Tweet postTweet(String username, String message) {
        Tweet tweet = buildTweet(username, message);
        tweetPersistenceCommand.postTweet(tweet);
        return tweet;
    }

    private Tweet buildTweet(String username, String message) {
        return Tweet.builder()
                    .message(message)
                    .postedByUsername(username)
                    .postedOn(currentDateTime.now())
                    .build();
    }
}
