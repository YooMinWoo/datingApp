package com.mintae.dating.feature.service;

import com.mintae.dating.feature.dto.FeatureDTO;
import com.mintae.dating.feature.mapper.FeatureMapper;
import com.mintae.dating.feature.vo.Feature;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
