package com.mintae.dating.dto;

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
