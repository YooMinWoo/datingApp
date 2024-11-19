package com.mintae.dating.controller;

import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.service.VerificationProvider;
import com.mintae.dating.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final VerificationProvider verificationProvider;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupDTO signupDTO){
        try{
            userService.signupProcess(signupDTO);
            return "회원가입 성공!";
        }catch (Exception e){
            return "회원가입 실패!";
        }
    }

    @GetMapping("/verification")
    public String verification(@RequestParam("mobile") String mobile){
        verificationProvider.setVerification(mobile);
        return verificationProvider.getVerification().split(" ")[1];
    }

}
