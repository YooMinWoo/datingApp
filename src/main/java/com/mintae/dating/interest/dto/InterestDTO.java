package com.mintae.dating.interest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestDTO {
    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private Boolean custom;
}
