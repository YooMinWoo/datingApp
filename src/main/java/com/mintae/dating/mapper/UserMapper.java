package com.mintae.dating.mapper;


import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.security.oauth.dto.OAuth2DTO;
import com.mintae.dating.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByMobile(String mobile);

    void signup(SignupDTO.SignupDTO_User signupDTO);

    OAuth2DTO findByMobile_OAuth(Long user_id);
}
