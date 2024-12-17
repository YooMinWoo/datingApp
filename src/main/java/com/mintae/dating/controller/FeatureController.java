package com.mintae.dating.controller;

import com.mintae.dating.dto.ApiResponse;
import com.mintae.dating.dto.FeatureDTO;
import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.exception.CustomException;
import com.mintae.dating.service.FeatureService;
import com.mintae.dating.service.InterestService;
import com.mintae.dating.vo.Feature;
import com.mintae.dating.vo.Interest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping("/feature")
    public ResponseEntity<?> getInterests(){
        try {
            List<Feature> features = featureService.getFeatures();
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "조회 성공", features);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/feature")
    public ResponseEntity<?> insertInterest(@RequestBody @Valid List<FeatureDTO> featureDTOs){
        try {
            featureService.insertFeature(featureDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "생성 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "생성 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/feature")
    public ResponseEntity<?> deleteInterest(@RequestParam("id") List<Long> ids){
        try {
            featureService.deleteFeature(ids);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "삭제 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "삭제 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/feature")
    public ResponseEntity<?> updateInterest(@RequestBody @Valid List<FeatureDTO> featureDTOs){
        try {
            featureService.updateFeature(featureDTOs);
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "수정 성공", null);
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        } catch (CustomException e){
            ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.FORBIDDEN.value(), "수정 중 에러 발생", null);
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(apiResponse);
        }
    }
}
