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
        List<Feature> features = featureService.getFeatures();
        return ResponseEntity.ok(ApiResponse.success("조회 성공", features));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/feature")
    public ResponseEntity<?> insertInterest(@RequestBody @Valid List<FeatureDTO> featureDTOs){
        featureService.insertFeature(featureDTOs);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "생성 성공", null);
        return ResponseEntity.ok(ApiResponse.success("생성 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/feature")
    public ResponseEntity<?> deleteInterest(@RequestParam("id") List<Long> ids){
        featureService.deleteFeature(ids);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "삭제 성공", null);
        return ResponseEntity.ok(ApiResponse.success("삭제 성공", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/feature")
    public ResponseEntity<?> updateInterest(@RequestBody @Valid List<FeatureDTO> featureDTOs){
        featureService.updateFeature(featureDTOs);
        ApiResponse<?> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), "수정 성공", null);
        return ResponseEntity.ok(ApiResponse.success("수정 성공", null));
    }
}
