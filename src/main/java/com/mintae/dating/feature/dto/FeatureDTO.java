package com.mintae.dating.feature.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeatureDTO {
    private Long id;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    private Boolean custom;
}
