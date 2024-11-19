package com.mintae.dating.mapper;


import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByMobile(String mobile);

    void signup(SignupDTO signupDTO);
}
