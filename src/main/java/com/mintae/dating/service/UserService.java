package com.mintae.dating.service;

import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public void signupProcess(SignupDTO signupDTO) throws Exception {
        String mobile = signupDTO.getMobile();
        if(existByMobile(mobile)) throw new Exception("이미 존재하는 계정입니다.");
        signupDTO.setRole("ROLE_USER");

        userMapper.signup(signupDTO);
    }

    private boolean existByMobile(String mobile) {
        return userMapper.findByMobile(mobile) != null;
    }


}
