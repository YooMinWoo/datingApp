package com.mintae.dating.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String mobile;
    private String password;
    private String role;
}
