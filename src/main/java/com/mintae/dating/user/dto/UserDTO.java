package com.mintae.dating.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {

    private Long id;
    private String provider;
    private String mobile;
    private String role;
}
