package com.mintae.dating.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
