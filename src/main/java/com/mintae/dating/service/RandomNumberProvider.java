package com.mintae.dating.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
@Service
public class RandomNumberProvider {
    private int rn;

    public void setRn() {
        this.rn = 100;
        System.out.println("설정된 비밀번호: "+rn);
    }

}
