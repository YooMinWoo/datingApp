package com.mintae.dating.controller;

import com.mintae.dating.dto.ApiResponse;
import com.mintae.dating.dto.TermDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.service.TermService;
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
public class TermController {

    private final TermService termService;

    @GetMapping("/term")
    public ResponseEntity<?> getTerms(){
        try {
            List<Term> terms = termService.getTemrs();
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "약관 조회 성공", terms);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "약관 조회 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/term")
    public ResponseEntity<?> insertTerm(@RequestBody @Valid List<TermDTO> termDTOs){
        try {
            termService.insertTerm(termDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "약관 생성 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "약관 생성 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/term")
    public ResponseEntity<?> deleteTerm(@RequestParam("id") List<Long> ids){
        try {
            termService.deleteTerm(ids);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "약관 삭제 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "약관 삭제 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/term")
    public ResponseEntity<?> updateTerm(@RequestBody @Valid List<TermDTO> termDTOs){
        try {
            termService.updateTerm(termDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "약관 수정 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "약관 수정 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }
}
