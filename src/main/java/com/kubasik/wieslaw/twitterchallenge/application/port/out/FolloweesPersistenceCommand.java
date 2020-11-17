package com.kubasik.wieslaw.twitterchallenge.application.port.out;

public interface FolloweesPersistenceCommand {
    void follow(String username, String usernameToFollow);
}
