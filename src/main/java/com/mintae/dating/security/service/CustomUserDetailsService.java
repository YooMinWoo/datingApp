package com.mintae.dating.security.service;

import com.mintae.dating.user.mapper.UserMapper;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.global.service.VerificationProvider;
import com.mintae.dating.user.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;
    private final VerificationProvider verificationProvider;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User user = userMapper.findByMobile(mobile);
        if(user != null){
            return new CustomUserDetails(user,verificationProvider);
        }
        return null;
    }
}
