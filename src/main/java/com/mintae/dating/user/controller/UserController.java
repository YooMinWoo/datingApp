package com.mintae.dating.user.controller;

import com.mintae.dating.global.dto.ApiResponse;
import com.mintae.dating.user.dto.SignupDTO;
import com.mintae.dating.security.user.CustomUserDetails;
import com.mintae.dating.global.service.VerificationProvider;
import com.mintae.dating.user.service.UserService;
import com.mintae.dating.user.vo.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO, @RequestPart("profile") List<MultipartFile> multipartFile) throws IOException {
        userService.existByMobile(signupDTO.getUser().getMobile());
        userService.checkVerification(signupDTO.getUser().getMobile() + " " + signupDTO.getUser().getVerification());
        userService.signupProcess(signupDTO, multipartFile);
        return ResponseEntity.status(HttpStatus.OK.value()).body(ApiResponse.success("회원가입 성공!", signupDTO));
    }

    @PostMapping("/value-test")
    public ResponseEntity<?> value_test(@RequestPart("profiles") List<MultipartFile> profiles,
                                        @RequestPart("signupDTO") @Valid SignupDTO signupDTO) throws IOException {
        userService.signupProcess(signupDTO, profiles);
        return ResponseEntity.ok(ApiResponse.success("회원가입 성공!", signupDTO));
    }

    // 사용 가능한 전화번호 체크
    @PostMapping("/check-mobile")
    public ResponseEntity<?> checkPhone(@RequestParam(value = "mobile") String mobile){
            userService.existByMobile(mobile);
            return ResponseEntity.ok(ApiResponse.success("사용 가능한 전화번호",null));
    }

    // 인증번호 일치 여부 (인증번호와 입력한 값이 일치하는지)
    @PostMapping("check-verification")
    public ResponseEntity<?> checkVerification(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "verification") String verification){
            userService.checkVerification(mobile+" "+verification);
            return ResponseEntity.ok(ApiResponse.success("인증번호가 일치합니다.", null));
    }


    @GetMapping("/verification")
    public ResponseEntity<?> verification(@RequestParam("mobile") String mobile){
        verificationProvider.setVerification(mobile);
        return ResponseEntity.ok(ApiResponse.success(verificationProvider.getVerification().split(" ")[1], verificationProvider.getVerification()));
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> myPage(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        User user = customUserDetails.getUser();
        return ResponseEntity.ok(ApiResponse.success("개인정보 조회", user));
    }
}
