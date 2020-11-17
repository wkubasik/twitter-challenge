package com.kubasik.wieslaw.twitterchallenge.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import static com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ExampleValues.USERNAME;

@Getter
@Setter
public class FolloweePostDto {

    @NotBlank
    @Schema(example = USERNAME)
    private String username;
}
