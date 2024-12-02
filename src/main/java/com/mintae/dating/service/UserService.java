package com.mintae.dating.service;

import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final VerificationProvider verificationProvider;

    public void signupProcess(SignupDTO signupDTO) throws Exception {

        signupDTO.setRole("ROLE_USER");
        userMapper.signup(signupDTO);
    }

    public void checkVerification(String verification){
        if(!verification.equals(verificationProvider.getVerification())) throw new CustomException("인증번호가 일치하지 않습니다.");
    }

    public void existByMobile(String mobile) {
        if(userMapper.findByMobile(mobile) != null) throw new CustomException("이미 존재하는 전화번호입니다.");
    }


}
