package com.mintae.dating.service;

import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final VerificationProvider verificationProvider;

    @Transactional
    public void signupProcess(SignupDTO.SignupDTO_User signupDTO) throws Exception {
        userMapper.signup(signupDTO);
    }

    public void checkVerification(String verification){
        if(!verification.equals(verificationProvider.getVerification())) throw new CustomException("인증번호가 일치하지 않습니다.");
    }

    public void existByMobile(String mobile) {
        if(userMapper.findByMobile(mobile) != null) throw new CustomException("이미 존재하는 전화번호입니다.");
    }


}
