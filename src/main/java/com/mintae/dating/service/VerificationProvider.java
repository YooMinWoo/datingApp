package com.mintae.dating.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Random;

@Getter
@Service
public class VerificationProvider {
    private String verification;

    public void setVerification(String mobile) {
        // 100,000에서 999,999 사이의 랜덤 숫자 생성
        this.verification = mobile + " " + String.valueOf(new Random().nextInt(900000) + 100000);
        System.out.println("제공된 인증번호: "+verification.split(" ")[1]);
    }

}
