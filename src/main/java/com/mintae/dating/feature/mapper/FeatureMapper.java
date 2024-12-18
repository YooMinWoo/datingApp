package com.mintae.dating.feature.mapper;

import com.mintae.dating.feature.dto.FeatureDTO;
import com.mintae.dating.feature.vo.Feature;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeatureMapper {


    List<Feature> getFeatures();

    void insertFeature(FeatureDTO featureDTO);

    void deleteFeature(Long id);

    void updateFeature(FeatureDTO featureDTO);
}
