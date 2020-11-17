package com.kubasik.wieslaw.twitterchallenge.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@RequiredArgsConstructor
public class Tweet implements Comparable<Tweet> {
    private final String postedByUsername;
    private final String message;
    private final LocalDateTime postedOn;

    @Override
    public int compareTo(Tweet o) {
        return o.getPostedOn().compareTo(this.getPostedOn());
    }
}
