package com.kubasik.wieslaw.twitterchallenge.adapter.web;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.FolloweePostDto;
import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.TweetDto;
import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.TweetPostDto;
import com.kubasik.wieslaw.twitterchallenge.annotations.ApiService;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.FollowUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.GetFolloweesTweetsUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.PostTweetUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.service.GetTweetsUseCaseService;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;
import com.kubasik.wieslaw.twitterchallenge.mappers.TweetMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.kubasik.wieslaw.twitterchallenge.mappers.TweetMapper.mapToTweetDto;

@ApiService
@RequiredArgsConstructor
public class UsersApiService {

    private final PostTweetUseCase postTweetUseCase;
    private final GetTweetsUseCaseService myTweetsUseCaseService;
    private final FollowUseCase followUseCase;
    private final GetFolloweesTweetsUseCase getFolloweesTweetsUseCase;

    public TweetDto postTweet(String username, TweetPostDto tweetPostDto) {
        Tweet tweet = postTweetUseCase.postTweet(username, tweetPostDto.getMessage().trim());
        return mapToTweetDto(tweet);
    }

    public List<TweetDto> getMyTweets(String username) {
        return myTweetsUseCaseService.getTweets(username).stream()
                .map(TweetMapper::mapToTweetDto)
                .collect(Collectors.toList());
    }

    public void follow(String username, FolloweePostDto followeePostDto) {
        followUseCase.follow(username, followeePostDto.getUsername().trim());
    }

    public List<TweetDto> getMyFolloweesTweets(String username) {
        return getFolloweesTweetsUseCase.getFolloweesTweets(username).stream()
                .map(TweetMapper::mapToTweetDto)
                .collect(Collectors.toList());
    }
}
