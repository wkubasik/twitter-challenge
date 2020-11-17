package com.kubasik.wieslaw.twitterchallenge.adapter.system;

import com.kubasik.wieslaw.twitterchallenge.annotations.Adapter;
import com.kubasik.wieslaw.twitterchallenge.application.port.out.CurrentDateTime;

import java.time.LocalDateTime;

@Adapter
public class CurrentDateTimeAdapter implements CurrentDateTime {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
