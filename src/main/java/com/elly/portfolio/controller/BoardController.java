package com.elly.portfolio.controller;

import java.util.HashMap;
import java.util.List;

import com.elly.portfolio.dto.ProjectAddDto;
import com.elly.portfolio.service.BoardService;
import com.elly.portfolio.vo.IntroList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    /**
     * 프로젝트 리스트 조회
     */
    @GetMapping("/list")
    public @ResponseBody List<IntroList> mainPage() throws Exception {
        return boardService.getAllBoardList();
    }
    
    /**
     * 선택한 프로젝트 상세보기 페이지
     */
    @GetMapping(value = "/detail/{no}")
    public @ResponseBody HashMap<String, Object> detailPage(@PathVariable(value = "no", required = true) int no) throws Exception {
        return boardService.getBoardSelectOne(no);
    }

    /**
     * 프로젝트 리스트 등록
     */
    @PostMapping(value = "/write")
    @ResponseStatus(code = HttpStatus.OK)
    public void write(@ModelAttribute ProjectAddDto projectAddDto) throws Exception {
        boardService.insertBoard(projectAddDto);
    }

    /**
     * 선택한 프로젝트 리스트 수정
     */
    @PostMapping(value = "/update/{no}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable(value = "no", required = true) int no, @ModelAttribute ProjectAddDto projectAddDto) throws Exception {
        boardService.updateBoard(projectAddDto);
    }

    /**
     * 선택한 프로젝트 리스트 삭제
     */
    @GetMapping(value = "/delete/{no}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable(value = "no", required = true) int no) throws Exception {
        boardService.deleteBoard(no);
    }
}
