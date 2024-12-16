package com.mintae.dating.mapper;

import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.dto.TermDTO;
import com.mintae.dating.vo.Interest;
import com.mintae.dating.vo.Term;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterestMapper {

    List<Interest> getInterests();

    void insertInterest(InterestDTO interestDTO);

    void deleteInterest(Long id);

    void updateInterest(InterestDTO interestDTO);

}
