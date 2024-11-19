package com.mintae.dating.security.service;

import com.mintae.dating.mapper.UserMapper;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User user = userMapper.findByMobile(mobile);
        if(user != null){
            return new CustomUserDetails(user);
        }
        return null;
    }
}
