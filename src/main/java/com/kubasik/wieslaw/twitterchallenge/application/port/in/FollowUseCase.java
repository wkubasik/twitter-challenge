package com.kubasik.wieslaw.twitterchallenge.application.port.in;

public interface FollowUseCase {
    void follow(String username, String usernameToFollow);
}
