package com.kubasik.wieslaw.twitterchallenge.adapter.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ExampleValues.DATE_TIME;
import static com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ExampleValues.DATE_TIME_VIEW_TYPE;
import static com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ExampleValues.ERROR_MESSAGE;
import static com.kubasik.wieslaw.twitterchallenge.utils.DateFormats.DATE_FORMAT;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorDto {

    @Schema(example = ERROR_MESSAGE)
    private final String message;

    @Schema(type = DATE_TIME_VIEW_TYPE, example = DATE_TIME)
    @JsonFormat(pattern = DATE_FORMAT)
    private final LocalDateTime timestamp;
}
