package com.mintae.dating.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class SignupDTO {
    /*
        1. user 테이블에 먼저 값을 넣은 뒤, user_id를 받아온다.
        2. 요청 값 모두에 user_id를 set한다.
        3. interest, feature의 경우 id가 없을 경우 커스텀 목록이다.
        4. 사용자 등록 유무에 true값(1)을 넣고, 해당 id를 가져와서 user_interest, user_feature에 set한다.
        5. 나머지 값 모두 insert


        interest와 feature 처리 방법
        1. request로 id, content를 받는다.
        2. 만일 id가 null이라면 custom인 것이고, 관심사 목록에 insert 하고 그 id 값을 받는다.
        3. user_id, interest_id 값을 받아서 user_interest에 insert한다.
     */
    private SignupDTO_User user;
    private List<SignupDTO_User_Term> user_terms;
    private SignupDTO_Preference preference;
    private List<SignupDTO_Interest> interests;
    private List<SignupDTO_User_Interest> user_interests = new ArrayList<>();
    private List<SignupDTO_Feature> features;
    private List<SignupDTO_User_Feature> user_features = new ArrayList<>();
    private List<SignupDTO_Profile> profile = new ArrayList<>();

//    private List<MultipartFile>



    public void setUser_Id(){
        Long id = user.getId();

        preference.setUser_id(id);

        for(SignupDTO_User_Term term : user_terms){
            term.setUser_id(id);
        }

        for(SignupDTO_Interest interest : interests){
            SignupDTO_User_Interest user_interest = new SignupDTO_User_Interest();
            user_interest.setUser_id(id);
            user_interest.setInterest_id(interest.getId());
            user_interests.add(user_interest);
        }

        for(SignupDTO_Feature feature : features){
            SignupDTO_User_Feature user_feature = new SignupDTO_User_Feature();
            user_feature.setUser_id(id);
            user_feature.setFeature_id(feature.getId());
            user_features.add(user_feature);
        }
    }

    public void setProfileList(List<MultipartFile> multipartFile) {
        for(MultipartFile file : multipartFile){
            SignupDTO_Profile signupDTO_profile = new SignupDTO_Profile();
            signupDTO_profile.setUser_id(user.getId());
            signupDTO_profile.setOriginal_name(file.getOriginalFilename());
            signupDTO_profile.setUuid(UUID.randomUUID().toString());
            signupDTO_profile.setPath("바탕화면");
            profile.add(signupDTO_profile);
        }
    }

    @Data
    public static class SignupDTO_User{
        private Long id;
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
    public static class SignupDTO_User_Term{
        private Long user_id;
        private Long term_id;
        private boolean agree;
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

    @Data
    public static class SignupDTO_Interest{
        private Long id;
        private String content;
        private boolean custom;
    }

    @Data
    public static class SignupDTO_User_Interest{
        private Long user_id;
        private Long interest_id;
        private boolean status;
    }

    @Data
    public static class SignupDTO_Feature{
        private Long id;
        private String content;
        private boolean custom;
    }

    @Data
    public static class SignupDTO_User_Feature{
        private Long user_id;
        private Long feature_id;
        private boolean status;
    }

    @Data
    public static class SignupDTO_Profile{
        private Long id;
        private Long user_id;
        private String path;
        private String original_name;
        private String uuid;
        private boolean status;
        private Date create_date;
        private Date update_date;
    }
}
