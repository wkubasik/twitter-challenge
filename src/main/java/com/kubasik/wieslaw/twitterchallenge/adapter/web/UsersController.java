package com.kubasik.wieslaw.twitterchallenge.adapter.web;

import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.FolloweePostDto;
import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.TweetDto;
import com.kubasik.wieslaw.twitterchallenge.adapter.web.dto.TweetPostDto;
import com.kubasik.wieslaw.twitterchallenge.config.swagger.ApiResponseBadRequest;
import com.kubasik.wieslaw.twitterchallenge.config.swagger.ApiResponseInternalServerError;
import com.kubasik.wieslaw.twitterchallenge.config.swagger.ApiResponseNoContent;
import com.kubasik.wieslaw.twitterchallenge.config.swagger.ApiResponseNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "User tweets API", description = "User tweets and followees.")
@RequestMapping(value = "users/{username}")
public class UsersController {

    private final UsersApiService apiService;

    @PostMapping(value = "tweet", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Create tweet")
    @ApiResponseBadRequest
    @ApiResponseInternalServerError
    @ApiResponse(responseCode = "201", description = "Created",
            content = @Content(schema = @Schema(implementation = TweetDto.class)))
    @ResponseStatus(HttpStatus.CREATED)
    public TweetDto postTweet(@Valid @NotBlank @PathVariable String username,
                              @Valid @RequestBody TweetPostDto tweetPostDto) {
        return apiService.postTweet(username, tweetPostDto);
    }

    @GetMapping(value = "tweets", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get user tweets")
    @ApiResponseNotFound
    @ApiResponseBadRequest
    @ApiResponseInternalServerError
    @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TweetDto.class))))
    public List<TweetDto> getMyTweets(@Valid @NotBlank @PathVariable String username) {
        return apiService.getMyTweets(username);
    }

    @PostMapping(value = "followees", consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Follow a user")
    @ApiResponseNotFound
    @ApiResponseNoContent
    @ApiResponseBadRequest
    @ApiResponseInternalServerError
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void follow(@Valid @NotBlank @PathVariable String username,
                       @Valid @RequestBody FolloweePostDto followeePostDto) {
        apiService.follow(username, followeePostDto);
    }

    @GetMapping(value = "followees/tweets", produces = APPLICATION_JSON_VALUE)
    @Operation(summary = "Get tweets from followees")
    @ApiResponseNotFound
    @ApiResponseBadRequest
    @ApiResponseInternalServerError
    @ApiResponse(responseCode = "200", description = "Ok",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TweetDto.class))))
    public List<TweetDto> getMyFolloweesTweets(@Valid @NotBlank @PathVariable String username) {
        return apiService.getMyFolloweesTweets(username);
    }
}
