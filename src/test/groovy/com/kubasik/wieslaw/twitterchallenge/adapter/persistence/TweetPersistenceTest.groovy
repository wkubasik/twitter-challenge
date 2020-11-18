package com.kubasik.wieslaw.twitterchallenge.adapter.persistence

import com.kubasik.wieslaw.twitterchallenge.application.port.out.TweetPersistenceCommand
import com.kubasik.wieslaw.twitterchallenge.domain.Tweet
import spock.lang.Specification

import java.time.LocalDateTime

class TweetPersistenceTest extends Specification {

    TweetPersistenceCommand persistence = new PersistenceAdapter()

    def "should add new user with posted tweet when does not exist"() {
        given:
            persistence.userTweets.clear()
            Tweet tweet = getTweet("josh", "my message")

        when:
            persistence.postTweet(tweet)

        then:
            persistence.userTweets.get(tweet.getPostedByUsername()).contains(tweet)
    }

    def "should add tweet to existing tweets when user post new tweet"() {
        given:
            def username = "josh"
            List<Tweet> tweets = [
                    getTweet(username, "message 1"),
                    getTweet(username, "message 2")
            ]
            persistence.userTweets.clear()
            persistence.userTweets.put(username, tweets)
            Tweet newTweet = getTweet(username, "message 3")

        when:
            persistence.postTweet(newTweet)

        then:
            List<Tweet> savedTweets = persistence.userTweets.get(username)
            savedTweets.size() == 3
            savedTweets.containsAll(tweets + newTweet)
    }

    def "should get tweets in reverse chronological order for user"() {
        given:
            def username = "username"
            def message = "message 2"
            LocalDateTime now = LocalDateTime.now()
            Tweet tweet1 = getTweet(username, message, now)
            Tweet tweet2 = getTweet(username, message, now.plusMinutes(3))
            Tweet tweet3 = getTweet(username, message, now.plusHours(1))
            Tweet tweet4 = getTweet(username, message, now.plusDays(2))
            persistence.postTweet(tweet2)
            persistence.postTweet(tweet4)
            persistence.postTweet(tweet1)
            persistence.postTweet(tweet3)

        when:
            def tweets = persistence.getTweetsByUsernameOrdered(username)

        then:
            tweets == [tweet4, tweet3, tweet2, tweet1]
    }

    def "should get user followees tweets in reverse chronological order"() {
        given:
            def user = "username"
            def message = "message"
            createUserByTweet(user, message)
            def followee1 = "username1"
            def followee2 = "username2"
            persistence.userFollowees.put(user, [followee1, followee2])

            LocalDateTime now = LocalDateTime.now()
            Tweet tweet1 = getTweet(followee1, message, now)
            Tweet tweet2 = getTweet(followee2, message, now.plusMinutes(3))
            Tweet tweet3 = getTweet(followee1, message, now.plusHours(1))
            Tweet tweet4 = getTweet(followee2, message, now.plusDays(2))
            Tweet tweet5 = getTweet(followee2, message, now.plusHours(200))
            persistence.postTweet(tweet2)
            persistence.postTweet(tweet4)
            persistence.postTweet(tweet1)
            persistence.postTweet(tweet3)
            persistence.postTweet(tweet5)

        when:
            def tweets = persistence.getFolloweesTweetsByUsernameOrdered(user)

        then:
            tweets == [tweet5, tweet4, tweet3, tweet2, tweet1]
    }

    private Tweet createUserByTweet(String user, String message) {
        persistence.postTweet(getTweet(user, message))
    }

    private static Tweet getTweet(String username, String message) {
        Tweet.builder()
                .postedByUsername(username)
                .message(message)
                .postedOn(LocalDateTime.now())
                .build()
    }

    private static Tweet getTweet(String username, String message, LocalDateTime postedOn) {
        Tweet.builder()
                .postedByUsername(username)
                .message(message)
                .postedOn(postedOn)
                .build()
    }

}
