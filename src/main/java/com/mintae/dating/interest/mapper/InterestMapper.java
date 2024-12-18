package com.mintae.dating.interest.mapper;

import com.mintae.dating.interest.dto.InterestDTO;
import com.mintae.dating.interest.vo.Interest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {

    List<Interest> getInterests();

    void insertInterest(InterestDTO interestDTO);

    void deleteInterest(Long id);

    void updateInterest(InterestDTO interestDTO);

}
