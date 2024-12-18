package com.mintae.dating.term.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Term {
    private Long id;
    private String name;
    private String content;
    private Boolean type;
    private Boolean status;
}
