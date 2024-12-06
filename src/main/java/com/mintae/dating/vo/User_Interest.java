package com.mintae.dating.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class User_Interest {

    private final Long user_id;
    private final Long interest_id;
    private final boolean status;

}
