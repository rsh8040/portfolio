package com.elly.portfolio.service;

import java.util.HashMap;
import java.util.List;

import com.elly.portfolio.dto.ProjectAddDto;
import com.elly.portfolio.mapper.BoardMapper;
import com.elly.portfolio.vo.IntroList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardMapper boardMapper;

    /**
     * 프로젝트 리스트 조회
     */
    public List<IntroList> getAllBoardList() throws Exception {
        return boardMapper.getAllBoardList();
    }

    /**
     * 선택한 프로젝트 상세보기
     */
    public ProjectAddDto getBoardSelectOne(Integer no) throws Exception {
        return boardMapper.getBoardSelectOne(no);
    }

    /**
     * 프로젝트 등록
     */
    @Transactional
    public void insertBoard(HashMap<String, Object> param) throws Exception {
        boardMapper.insertBoard(param);
        
    }

    /**
     * 선택한 프로젝트 수정
     */
    @Transactional
    public void updateBoard(HashMap<String, Object> param) throws Exception {
        boardMapper.updateBoard(param);
    }

    /**
     * 선택한 프로젝트 삭제
     */
    @Transactional
    public void deleteBoard(Integer no) throws Exception {
        boardMapper.deleteBoard(no);
        
    }
}
