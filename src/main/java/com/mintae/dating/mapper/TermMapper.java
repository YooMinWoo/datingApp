package com.mintae.dating.mapper;

import com.mintae.dating.dto.TermDTO;
import com.mintae.dating.vo.Term;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TermMapper {
    List<Term> getTerms();
    void insertTerm(TermDTO termDTO);
    void deleteTerm(Long id);
    void updateTerm(TermDTO termDTO);
}
