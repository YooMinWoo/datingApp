package com.mintae.dating.mapper;

import com.mintae.dating.dto.FeatureDTO;
import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.vo.Feature;
import com.mintae.dating.vo.Interest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeatureMapper {


    List<Feature> getFeatures();

    void insertFeature(FeatureDTO featureDTO);

    void deleteFeature(Long id);

    void updateFeature(FeatureDTO featureDTO);
}
