package com.kubasik.wieslaw.twitterchallenge.config.swagger;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.ErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponse(responseCode = "500", description = "server error",
        content = @Content(schema = @Schema(implementation = ErrorDto.class)))
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResponseInternalServerError {
}
