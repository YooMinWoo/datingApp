package com.mintae.dating.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
public class SignupDTO {
    private SignupDTO_User user;
    private List<SignupDTO_Term> term;
    private SignupDTO_Preference preference;

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
        private Long user_id;
        private Long agree;
    }

    @Data
    public static class SignupDTO_Preference{
        private Long user_id;
        private String gender;
        private Long min_age;
        private Long max_age;
        private String religion;
        private Long distance;
        private String alcohol;
        private String cigarette;
    }

}
