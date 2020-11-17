package com.kubasik.wieslaw.twitterchallenge.application.service;

import com.kubasik.wieslaw.twitterchallenge.annotations.UseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.in.FollowUseCase;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.FolloweesPersistenceCommand;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FollowUseCaseService implements FollowUseCase {

    private final FolloweesPersistenceCommand followeesPersistenceCommand;

    @Override
    public void follow(String username, String usernameToFollow) {
        followeesPersistenceCommand.follow(username, usernameToFollow);
    }
}
