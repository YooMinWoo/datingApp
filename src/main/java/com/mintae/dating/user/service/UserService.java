package com.mintae.dating.user.service;

import com.mintae.dating.global.service.VerificationProvider;
import com.mintae.dating.user.dto.SignupDTO;
import com.mintae.dating.global.exception.CustomException;
import com.mintae.dating.user.mapper.UserMapper;
import com.mintae.dating.user_interest.vo.User_Interest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final VerificationProvider verificationProvider;

    @Transactional
    public void signupProcess(SignupDTO signupDTO, List<MultipartFile> multipartFile) throws IOException {
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

        // 기본 회원 정보 insert 및 회원 id 가져오기
        userMapper.signup(signupDTO.getUser());

        // 관심사 insert(사용자 직접 추가의 경우만)
        insertInterest(signupDTO.getInterests());

        // 특징 insert(사용자 직접 추가의 경우만)
        insertFeature(signupDTO.getFeatures());

        // id 값들 세팅하기
        signupDTO.setUser_Id();

        // profile dto 값 세팅
        signupDTO.setProfileList(multipartFile);

        // profile 파일 업로드
        profile_upload(signupDTO.getProfile());

        // 프로필 insert
        insert_profile(signupDTO.getProfile());

        // 약관 insert
        insertUser_Term(signupDTO.getUser_terms());

        // user_interest에 값 추가
        insertUser_Interest(signupDTO.getUser_interests());

        // user_features에 값 추가
        insertUser_Feature(signupDTO.getUser_features());
    }

    private void profile_upload(List<SignupDTO.SignupDTO_Profile> profiles) throws IOException {
        if(profiles.isEmpty()) throw new RuntimeException();
        for(SignupDTO.SignupDTO_Profile profile : profiles){
            // 파일 업로드
            profile.getFile().transferTo(new File(profile.getSaved_path()));
        }
    }

    private void insert_profile(List<SignupDTO.SignupDTO_Profile> profiles){
        for(SignupDTO.SignupDTO_Profile profile : profiles){
            userMapper.insertProfile(profile);
        }
    };

    private void insertUser_Term(List<SignupDTO.SignupDTO_User_Term> user_terms) {
        for (SignupDTO.SignupDTO_User_Term user_term : user_terms){
            userMapper.insertUser_Term(user_term);
        }
    }

    private void insertUser_Feature(List<SignupDTO.SignupDTO_User_Feature> user_features) {
        for (SignupDTO.SignupDTO_User_Feature user_feature : user_features){
            userMapper.insertUser_Feature(user_feature);
        }
    }

    private void insertUser_Interest(List<SignupDTO.SignupDTO_User_Interest> user_interests) {
        for (SignupDTO.SignupDTO_User_Interest user_interest : user_interests){
            userMapper.insertUser_Interest(user_interest);
        }
    }

    private List<User_Interest> getUser_Interest(SignupDTO.SignupDTO_User user){
        return userMapper.getUser_Interest(user);
    }

//    public List<Interest> getInterest(List<SignupDTO.SignupDTO_Interest> interests){
//        List<Interest> interestList = new ArrayList<>();
//        for(SignupDTO.SignupDTO_Interest interest : interests){
//            interestList.add(userMapper.getInterest(interest.getId()));
//        }
//        return interestList;
//    }

    public void insertInterest(List<SignupDTO.SignupDTO_Interest> interests){
        for (SignupDTO.SignupDTO_Interest interest : interests){
            if(interest.getId() == null) userMapper.insertInterest(interest);
        }
    }

    private void insertFeature(List<SignupDTO.SignupDTO_Feature> features) {
        for (SignupDTO.SignupDTO_Feature feature : features){
            if(feature.getId() == null) userMapper.insertFeature(feature);
        }
    }

    public void checkVerification(String verification){
        if(!verification.equals(verificationProvider.getVerification())) throw new CustomException("인증번호가 일치하지 않습니다.");
    }

    public void existByMobile(String mobile) {
        if(userMapper.findByMobile(mobile) != null) throw new CustomException("이미 존재하는 전화번호입니다.");
    }

}
