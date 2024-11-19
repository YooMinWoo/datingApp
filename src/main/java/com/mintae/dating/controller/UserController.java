package com.mintae.dating.controller;

import com.mintae.dating.dto.SignupDTO;
import com.mintae.dating.service.RandomNumberProvider;
import com.mintae.dating.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RandomNumberProvider randomNumberProvider;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupDTO signupDTO){
        try{
            userService.signupProcess(signupDTO);
            return "회원가입 성공!";
        }catch (Exception e){
            return "회원가입 실패!";
        }
    }

    @GetMapping("/sendRandomNumber")
    public int sendRandomNumber(){
        randomNumberProvider.setRn();
        System.out.println("/sendRandomNumber get매핑: "+randomNumberProvider.getRn());
        return randomNumberProvider.getRn();
    }

}
