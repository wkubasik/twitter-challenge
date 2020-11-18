package com.kubasik.wieslaw.twitterchallenge.adapter.persistence

import com.kubasik.wieslaw.twitterchallenge.application.port.out.FolloweesPersistenceCommand
import com.kubasik.wieslaw.twitterchallenge.exceptions.UsernameNotFoundException
import spock.lang.Specification

import java.util.stream.Stream

class FolloweesPersistenceCommandTest extends Specification {

    private static final String USERNAME = "josh"
    private static final String USERNAME_TO_FOLLOW = "alice"

    FolloweesPersistenceCommand command = new PersistenceAdapter()

    def "should throw when username not exist"() {
        given:
            command.userTweets.clear()

        when:
            command.follow(USERNAME, USERNAME_TO_FOLLOW)

        then:
            def error = thrown(UsernameNotFoundException)
            error.message.contains(USERNAME)
    }

    def "should throw when username to follow not exist"() {
        given:
            command.userTweets.clear()
            putUsernamesToUsers(USERNAME)

        when:
            command.follow(USERNAME, USERNAME_TO_FOLLOW)

        then:
            def error = thrown(UsernameNotFoundException)
            error.message.contains(USERNAME_TO_FOLLOW)
    }

    def "should add to followees map when no followees yet"() {
        given:
            command.userTweets.clear()
            putUsernamesToUsers(USERNAME, USERNAME_TO_FOLLOW)
            command.userFollowees.clear()

        when:
            command.follow(USERNAME, USERNAME_TO_FOLLOW)

        then:
            command.userFollowees.get(USERNAME).contains(USERNAME_TO_FOLLOW)
    }

    def "should add multiple followees to map when user follow multiple users"() {
        given:
            command.userTweets.clear()
            def otherUsername = "other_user"
            putUsernamesToUsers(USERNAME, USERNAME_TO_FOLLOW, otherUsername)

        when:
            command.follow(USERNAME, USERNAME_TO_FOLLOW)
            command.follow(USERNAME, otherUsername)

        then:
            command.userFollowees.get(USERNAME).containsAll([USERNAME_TO_FOLLOW, otherUsername])
    }

    private void putUsernamesToUsers(String... usernames) {
        Stream.of(usernames).forEach(username -> command.userTweets.put(username, Collections.emptyList()))
    }

}
