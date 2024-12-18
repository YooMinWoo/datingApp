package com.mintae.dating.feature.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Feature {

    private final Long id;
    private final String content;
    private final Boolean custom;
}
