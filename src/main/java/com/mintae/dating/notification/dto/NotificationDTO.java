package com.mintae.dating.notification.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotificationDTO {
    private Long id;

    private Long sender_id;
    @NotNull
    private Long receiver_id;
    @NotNull
    private Content content;

    private Date create_date;
    private Date read_date;
}

enum Content{
    LIKE,
    ATTENTION
}
