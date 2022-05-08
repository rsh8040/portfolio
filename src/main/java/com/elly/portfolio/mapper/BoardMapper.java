package com.elly.portfolio.mapper;

import java.util.HashMap;
import java.util.List;

import com.elly.portfolio.dto.ProjectAddDto;
import com.elly.portfolio.vo.IntroList;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    /**
     * 프로젝트 리스트 조회
     */
    public List<IntroList> getAllBoardList() throws Exception;

    /**
     * 선택한 프로젝트 상세보기
     */
    public HashMap<String, Object> getBoardSelectOne(int no) throws Exception;

    /**
     * 프로젝트 리스트 등록
     */
    public void insertBoard(ProjectAddDto projectAddDto) throws Exception;

    /**
     * 선택한 프로젝트 리스트 수정
     */
    public void updateBoard(ProjectAddDto projectAddDto) throws Exception;

    /**
     * 선택한 프로젝트 리스트 삭제
     */
    public void deleteBoard(int no) throws Exception;
}
