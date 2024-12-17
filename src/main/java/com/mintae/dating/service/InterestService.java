package com.mintae.dating.service;

import com.mintae.dating.dto.InterestDTO;
import com.mintae.dating.dto.TermDTO;
import com.mintae.dating.mapper.InterestMapper;
import com.mintae.dating.mapper.TermMapper;
import com.mintae.dating.vo.Interest;
import com.mintae.dating.vo.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestMapper interestMapper;

    public List<Interest> getInterests(){
        return interestMapper.getInterests();
    }

    public void insertInterest(List<InterestDTO> interestDTOs){
        for(InterestDTO interestDTO : interestDTOs){
            interestMapper.insertInterest(interestDTO);
        }
    }

    public void deleteInterest(List<Long> ids) {
        for(Long id : ids) {
            interestMapper.deleteInterest(id);
        }
    }

    public void updateInterest(List<InterestDTO> interestDTOs) {
        for(InterestDTO interestDTO : interestDTOs) {
            interestMapper.updateInterest(interestDTO);
        }
    }
}