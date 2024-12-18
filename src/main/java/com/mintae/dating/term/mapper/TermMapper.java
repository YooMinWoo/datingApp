package com.mintae.dating.term.mapper;

import com.mintae.dating.term.dto.TermDTO;
import com.mintae.dating.term.vo.Term;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermMapper {
    List<Term> getTerms();
    void insertTerm(TermDTO termDTO);
    void deleteTerm(Long id);
    void updateTerm(TermDTO termDTO);
}
