package com.mintae.dating.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Valid
    @NotNull(message = "회원 정보를 입력해주세요.")
    private SignupDTO_User user;

    @Valid
    @NotNull(message = "약관을 체크해주세요.")
    @Size(min = 1, message = "약관을 체크해주세요.")
    private List<SignupDTO_User_Term> user_terms;

    @Valid
    private SignupDTO_Preference preference;

    @Valid
    @NotNull(message = "관심사는 적어도 하나 이상 입력해야 합니다.")
    @Size(min = 1, message = "관심사는 적어도 하나 이상 입력해야 합니다.")
    private List<SignupDTO_Interest> interests;

    private List<SignupDTO_User_Interest> user_interests = new ArrayList<>();

    @Valid
    @NotNull(message = "특징은 적어도 하나 이상 입력해야 합니다.")
    @Size(min = 1, message = "특징은 적어도 하나 이상 입력해야 합니다.")
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

            String original_name = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String extension = original_name.substring(original_name.lastIndexOf("."));
            String saved_name = uuid + extension;
            String saved_path = signupDTO_profile.getPROFILE_DIR() + saved_name;

            signupDTO_profile.setUser_id(user.getId());
            signupDTO_profile.setOriginal_name(original_name);
            signupDTO_profile.setSaved_name(saved_name);
            signupDTO_profile.setSaved_path(saved_path);
            signupDTO_profile.setFile(file);

            profile.add(signupDTO_profile);
        }
    }

    @Data
    public static class SignupDTO_User{
        private Long id;
        @Pattern(regexp = "^010-\\d{4}-\\d{4}$")
        @NotNull(message = "휴대폰 번호는 필수 입력값입니다.")
        private String mobile;

        @NotBlank(message = "인증번호는 필수 입력값입니다.")
        private String verification;

        @NotNull(message = "성별은 필수 입력 사항입니다.")
        private Gender gender;

        @DateTimeFormat(pattern = "yyyy-MM-dd") // 날짜 형식 지정
        @NotNull(message = "생년월일은 필수 입력값입니다.")
        private Date birth_date;

        @NotBlank(message = "주소는 필수 입력값입니다.")
        private String address;

        @NotNull(message = "키는 필수 입력값입니다.")
        @Positive(message = "올바른 키를 입력해주세요.")
        private Integer height;

        @NotNull(message = "학력은 필수 입력값입니다.")
        private Education education;

        @NotBlank(message = "직업은 필수 입력값입니다.")
        private String job;

        @NotNull(message = "종교는 필수 입력값입니다.")
        private Religion religion;

        @NotNull(message = "음주빈도는 필수 입력값입니다.")
        private Alcohol alcohol;

        @NotNull(message = "흡연빈도는 필수 입력값입니다.")
        private Cigarette cigarette;
    }

    @Data
    public static class SignupDTO_User_Term{
        private Long user_id;
        @NotNull(message = "약관 에러 발생")
        private Long term_id;

        @NotNull(message = "약관 체크 에러 발생")
        private Boolean agree;
    }

    @Data
    public static class SignupDTO_Preference{
        private Long user_id;

        @NotNull(message = "성별은 필수 입력 사항입니다.")
        private Gender gender;

        @NotNull(message = "최소 나이는 필수 입력값입니다.")
        @Positive(message = "최소 나이는 양수여야 합니다.")
        private Integer min_age;

        @NotNull(message = "최대 나이는 필수 입력값입니다.")
        @Positive(message = "최대 나이는 양수여야 합니다.")
        private Integer max_age;

        @NotNull(message = "종교는 필수 입력값입니다.")
        private Religion religion;

        private Long distance;

        @NotNull(message = "음주빈도는 필수 입력값입니다.")
        private Alcohol alcohol;

        @NotNull(message = "흡연빈도는 필수 입력값입니다.")
        private Cigarette cigarette;

        @AssertTrue(message = "최소 나이는 최대 나이보다 클 수 없습니다.")
        public boolean isAgeRangeValid() {
            return min_age <= max_age;
        }
    }

    @Data
    public static class SignupDTO_Interest{
        private Long id;
        private String content;
        private Boolean custom;

        @AssertTrue(message = "흥미선택 중 에러발생.")
        public boolean isInterestValid() {
            if(id == null && content.equals("")) return false;
            return true;
        }
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

        @AssertTrue(message = "특징 선택 중 에러발생.")
        public boolean isInterestValid() {
            if(id == null && content.equals("")) return false;
            return true;
        }
    }

    @Data
    public static class SignupDTO_User_Feature{
        private Long user_id;
        private Long feature_id;
        private boolean status;
    }

    @Data
    public static class SignupDTO_Profile{
        private final String PROFILE_DIR = "C:\\Users\\CODELINE\\Desktop\\개인파일\\profiles\\";

        private Long id;
        private Long user_id;
        private String original_name;
        private String saved_name;
        private String saved_path;
        private boolean status;
        private Date create_date;
        private Date update_date;

        private MultipartFile file;
    }
}
