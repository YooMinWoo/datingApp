package com.mintae.dating.mapper;


import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.security.oauth.dto.OAuth2DTO;
import com.mintae.dating.vo.Interest;
import com.mintae.dating.vo.User;
import com.mintae.dating.vo.User_Interest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByMobile(String mobile);

    void signup(SignupDTO.SignupDTO_User signupDTO);

    OAuth2DTO findByMobile_OAuth(Long user_id);

    void insertTest(TestDTO testDTO);

    TestDTO getTest(Long id);

    void insertInterest(SignupDTO.SignupDTO_Interest interest);

    Interest getInterest(Long id);

    void insertUser_Interest(SignupDTO.SignupDTO_User_Interest user_interest);

    List<User_Interest> getUser_Interest(SignupDTO.SignupDTO_User user);

    void insertUser_Feature(SignupDTO.SignupDTO_User_Feature user_feature);

    void insertFeature(SignupDTO.SignupDTO_Feature feature);

    void insertUser_Term(SignupDTO.SignupDTO_User_Term user_term);
}
