package com.mintae.dating.interest.controller;

import com.mintae.dating.global.dto.ApiResponse;
import com.mintae.dating.interest.dto.InterestDTO;
import com.mintae.dating.interest.service.InterestService;
import com.mintae.dating.interest.vo.Interest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @GetMapping("/interest")
    public ResponseEntity<?> getInterests(){
        List<Interest> interests = interestService.getInterests();
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "조회 성공", interests);
        return ResponseEntity.ok(ApiResponse.success("조회 성공", interests));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/interest")
    public ResponseEntity<?> insertInterest(@RequestBody @Valid List<InterestDTO> InterestDTOs){
        interestService.insertInterest(InterestDTOs);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "생성 성공", null);
        return ResponseEntity.ok(ApiResponse.success("생성 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/interest")
    public ResponseEntity<?> deleteInterest(@RequestParam("id") List<Long> ids){
        interestService.deleteInterest(ids);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "삭제 성공", null);
        return ResponseEntity.ok(ApiResponse.success("삭제 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/interest")
    public ResponseEntity<?> updateInterest(@RequestBody @Valid List<InterestDTO> interestDTOs){
        interestService.updateInterest(interestDTOs);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "수정 성공", null);
        return ResponseEntity.ok(ApiResponse.success("수정 성공", null));
    }
}
