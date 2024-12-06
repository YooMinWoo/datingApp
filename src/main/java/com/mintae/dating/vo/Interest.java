package com.mintae.dating.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Interest {

    private final Long id;
    private final String content;
    private final boolean custom;
}
