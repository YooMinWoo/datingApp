package com.mintae.dating.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TermDTO {

    private Long id;

    @NotBlank(message = "약관 이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "약관 내용을 입력해주세요.")
    private String content;
    @NotNull(message = "약관 필수 여부를 입력해주세요.")
    private Boolean type;
    @NotNull(message = "약관 사용 여부를 입력해주세요.")
    private Boolean status;

}
