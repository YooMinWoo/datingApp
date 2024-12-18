package com.mintae.dating.security.user;


import com.mintae.dating.global.service.VerificationProvider;
import com.mintae.dating.user.vo.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {

    private User user;
    private VerificationProvider verificationProvider;

    @Autowired
    public CustomUserDetails(User user, VerificationProvider verificationProvider) {
        this.user = user;
        this.verificationProvider = verificationProvider;
    }

    @Autowired
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return verificationProvider.getVerification();
    }
    @Override
    public String getUsername() {
        return user.getMobile();
    }

    @Override
    public String getName() {
        return "";
    }
}
