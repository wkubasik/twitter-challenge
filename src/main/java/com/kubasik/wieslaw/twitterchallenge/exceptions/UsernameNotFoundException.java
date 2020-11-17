package com.kubasik.wieslaw.twitterchallenge.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super(String.format("Username %s not found", username));
    }
}
