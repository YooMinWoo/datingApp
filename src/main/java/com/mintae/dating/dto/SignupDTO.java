package com.mintae.dating.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class SignupDTO {
    private SignupDTO_User user;
    private SignupDTO_Term term;

    @Data
    public static class SignupDTO_User{
        private String mobile;
        private String verification;
        private String gender;
        private Date birth_date;
        private String address;
        private Integer height;
        private String education;
        private String job;
        private String religion;
        private String alcohol;
        private String cigarette;
    }

    @Data
    public static class SignupDTO_Term{
        private Long id;
        private Long agree;
    }

}
