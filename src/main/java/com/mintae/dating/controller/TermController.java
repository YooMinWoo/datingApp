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
        List<Term> terms = termService.getTemrs();
        return ResponseEntity.ok(ApiResponse.success("약관 조회 성공", terms));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/term")
    public ResponseEntity<?> insertTerm(@RequestBody @Valid List<TermDTO> termDTOs){
        termService.insertTerm(termDTOs);
        return ResponseEntity.ok(ApiResponse.success("약관 생성 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/term")
    public ResponseEntity<?> deleteTerm(@RequestParam("id") List<Long> ids){
        termService.deleteTerm(ids);
        return ResponseEntity.ok(ApiResponse.success("약관 삭제 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/term")
    public ResponseEntity<?> updateTerm(@RequestBody @Valid List<TermDTO> termDTOs){
        termService.updateTerm(termDTOs);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "약관 수정 성공", null);
        return ResponseEntity.ok(ApiResponse.success("약관 수정 성공", null));
    }
}
