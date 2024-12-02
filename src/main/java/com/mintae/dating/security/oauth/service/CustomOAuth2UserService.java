package com.mintae.dating.security.oauth.service;

import com.mintae.dating.exception.ProviderMismatchException;
import com.mintae.dating.exception.UserNotRegisteredException;
import com.mintae.dating.mapper.UserMapper;
import com.mintae.dating.security.oauth.dto.*;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        /*
         oauth에서 전화번호를 가져온 뒤, 해당 전화번호가 user 테이블에 있는지 조회
         - 있을 경우
            user에 있는 회원 id를 가져온 뒤, oauth 테이블에 조회하여  provider 컬럼과 현재 provider가 맞는지 조회
            맞으면 로그인, 틀리면 에러

         없을 경우
            회원가입이 되어있지 않다는 에러
         */



        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")){
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if(registrationId.equals("google")){
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }
//        System.out.println(oAuth2Response);
        String mobile = oAuth2Response.getMobile();
        String provider = oAuth2Response.getProvider();
        User user = userMapper.findByMobile(mobile);

        // 없는 경우
        if (user == null) throw new UserNotRegisteredException("회원가입이 되어있지 않습니다.");
        else {
            OAuth2DTO oAuth2DTO = userMapper.findByMobile_OAuth(user.getId());
            if(oAuth2DTO == null) throw new UserNotRegisteredException("연동된 계정이 없습니다. 연동하시겠습니까?");

            // 소셜로그인 요청 provider와 기존에 연동한 provider가 일치할 경우 로그인 성공
            if(oAuth2DTO.getProvider().equals(provider)) {
                return new CustomUserDetails(user);
            } else{
                // 소셜로그인 요청 provider와 기존에 연동한 provider가 일치하지 않을 경우 에러
                throw new ProviderMismatchException("다른 소셜로그인과 이미 연동되어 있습니다.");
            }

        }
    }
}
