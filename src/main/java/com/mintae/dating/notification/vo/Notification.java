package com.mintae.dating.notification.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class Notification {
    private final Long id;
    private final Long sender_id;
    private final Long receiver_id;
    private final String content;
    private final Date create_date;
    private final Date read_date;
}
