package com.kubasik.wieslaw.twitterchallenge.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ExampleValues.TWEET_MESSAGE;

@Getter
@Setter
public class TweetPostDto {

    @NotBlank
    @Schema(example = TWEET_MESSAGE)
    @Size(min = 1, max = 140)
    private String message;
}
