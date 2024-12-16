package com.mintae.dating.controller;

import com.mintae.dating.dto.ApiResponse;
import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.service.VerificationProvider;
import com.mintae.dating.service.UserService;
import com.mintae.dating.vo.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final VerificationProvider verificationProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO, @RequestPart("profile") List<MultipartFile> multipartFile){

        try{
            userService.existByMobile(signupDTO.getUser().getMobile());
            userService.checkVerification(signupDTO.getUser().getMobile() + " " + signupDTO.getUser().getVerification());
            userService.signupProcess(signupDTO, multipartFile);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "회원가입 성공!", signupDTO);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), signupDTO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);
        } catch (Exception e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "회원가입 실패!", signupDTO);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);
        }
    }

    @PostMapping("/value-test")
    public void value_test(@RequestPart("profiles") List<MultipartFile> profiles,
                           @RequestPart("signupDTO") @Valid SignupDTO signupDTO) throws IOException {
        userService.signupProcess(signupDTO, profiles);
    }

    // 사용 가능한 전화번호 체크
    @PostMapping("/check-mobile")
    public ResponseEntity<?> checkPhone(@RequestParam(value = "mobile") String mobile){
        try{
            userService.existByMobile(mobile);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "사용 가능한 전화번호", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.CONFLICT.value(), e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body(apiResponse);
        }
    }

    // 인증번호 일치 여부 (인증번호와 입력한 값이 일치하는지)
    @PostMapping("check-verification")
    public ResponseEntity<?> checkVerification(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "verification") String verification){
        try {
            userService.checkVerification(mobile+" "+verification);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "인증번호가 일치합니다.", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "인증번호가 일치하지 않습니다.", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(apiResponse);
        }
    }


    @GetMapping("/verification")
    public ResponseEntity<?> verification(@RequestParam("mobile") String mobile){
        verificationProvider.setVerification(mobile);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), verificationProvider.getVerification().split(" ")[1], verificationProvider.getVerification());
        return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> myPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = customUserDetails.getUser();
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "개인정보 조회", user);
        return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
    }
}
