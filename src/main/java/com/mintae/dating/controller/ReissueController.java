package com.mintae.dating.controller;

import com.mintae.dating.security.jwt.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final JwtUtil jwtUtil;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response){

        String refreshToken = null;
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("refresh")){
                    refreshToken = cookie.getValue();
                }
            }
        }
        if(refreshToken == null){
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }
        //토큰 소멸 시간 검증
        try{
            jwtUtil.isExpired(refreshToken);
            // 토큰이 refresh인지 확인
            String category = jwtUtil.getCategory(refreshToken);

            if(!category.equals("refresh")){
                return new ResponseEntity<>("유효하지 않은 리프레시토큰입니다.", HttpStatus.BAD_REQUEST);
            }
        } catch(ExpiredJwtException e){
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        } catch(JwtException e){
            return new ResponseEntity<>("유효하지 않은 토큰입니다.", HttpStatus.BAD_REQUEST);
        }

        String mobile = jwtUtil.getMobile(refreshToken);
        String role = jwtUtil.getRole(refreshToken);

        //make new JWT
        String newAccessToken = jwtUtil.createJwt("access", mobile, role, 60*60*1000L);
        String newRefreshToken = jwtUtil.createJwt("refresh", mobile, role, 24*60*60*1000L);

        response.setHeader("access", newAccessToken);
        response.addCookie(createCookie("refresh", newRefreshToken));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
