package com.mintae.dating.term.service;

import com.mintae.dating.term.dto.TermDTO;
import com.mintae.dating.term.mapper.TermMapper;
import com.mintae.dating.term.vo.Term;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermMapper termMapper;

    public List<Term> getTemrs(){
        return termMapper.getTerms();
    }

    public void insertTerm(List<TermDTO> termDTOs){
        for(TermDTO termDTO : termDTOs) {
            termMapper.insertTerm(termDTO);
        }
    }

    public void deleteTerm(List<Long> ids) {
        for(Long id : ids){
            termMapper.deleteTerm(id);
        }
    }

    public void updateTerm(List<TermDTO> termDTOs) {
        for(TermDTO termDTO : termDTOs){
            termMapper.updateTerm(termDTO);
        }
    }
}
