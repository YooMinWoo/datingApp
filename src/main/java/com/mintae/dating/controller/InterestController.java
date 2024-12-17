package com.mintae.dating.controller;

import com.mintae.dating.dto.ApiResponse;
import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.dto.TermDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.service.InterestService;
import com.mintae.dating.service.TermService;
import com.mintae.dating.vo.Interest;
import com.mintae.dating.vo.Term;
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
        try {
            List<Interest> interests = interestService.getInterests();
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "조회 성공", interests);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/interest")
    public ResponseEntity<?> insertInterest(@RequestBody @Valid List<InterestDTO> InterestDTOs){
        try {
            interestService.insertInterest(InterestDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "생성 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "생성 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/interest")
    public ResponseEntity<?> deleteInterest(@RequestParam("id") List<Long> ids){
        try {
            interestService.deleteInterest(ids);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "삭제 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "삭제 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/interest")
    public ResponseEntity<?> updateInterest(@RequestBody @Valid List<InterestDTO> interestDTOs){
        try {
            interestService.updateInterest(interestDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "수정 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "수정 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }
}
