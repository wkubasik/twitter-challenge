package com.kubasik.wieslaw.twitterchallenge.mappers;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.TweetDto;
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet;

public class TweetMapper {
    public static TweetDto mapToTweetDto(Tweet tweet) {
        return TweetDto.builder()
                .message(tweet.getMessage())
                .postedByUsername(tweet.getPostedByUsername())
                .postedOn(tweet.getPostedOn())
                .build();
    }
}
