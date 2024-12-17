package com.mintae.dating.service;

import com.mintae.dating.dto.FeatureDTO;
import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.mapper.FeatureMapper;
import com.mintae.dating.mapper.InterestMapper;
import com.mintae.dating.vo.Feature;
import com.mintae.dating.vo.Interest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureMapper featureMapper;

    public List<Feature> getFeatures(){
        return featureMapper.getFeatures();
    }

    public void insertFeature(List<FeatureDTO> featureDTOs){
        for(FeatureDTO featureDTO : featureDTOs){
            featureMapper.insertFeature(featureDTO);
        }
    }

    public void deleteFeature(List<Long> ids) {
        for(Long id : ids) {
            featureMapper.deleteFeature(id);
        }
    }

    public void updateFeature(List<FeatureDTO> featureDTOs) {
        for(FeatureDTO featureDTO : featureDTOs) {
            featureMapper.updateFeature(featureDTO);
        }
    }
}
