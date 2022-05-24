package com.elly.portfolio.controller;

import java.util.HashMap;
import java.util.List;

import com.elly.portfolio.dto.ProjectAddDto;
import com.elly.portfolio.service.BoardService;
import com.elly.portfolio.vo.IntroList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String mainPage(Model model) throws Exception {
        List<IntroList> boardList = boardService.getAllBoardList();
        model.addAttribute("boardList" , boardList);
        return "boardList.html";
    }
    
    /**
     * 선택한 프로젝트 상세보기 페이지
     */
    @GetMapping("/detail/{no}")
    public String detail(@PathVariable(value = "no", required = true) Integer no, Model model) throws Exception {
        ProjectAddDto board = boardService.getBoardSelectOne(no);
        System.out.println("@@@"+boardService.getBoardSelectOne(no));
        model.addAttribute("board", board);
        return "boardDetail.html";
    }

    /**
     * 프로젝트 리스트 등록
     */
    @PostMapping("/write")
    public String write(ProjectAddDto projectAddDto) throws Exception {
        boardService.insertBoard(projectAddDto);
        return "redirect:/board/list";
    }

    /**
     * 선택한 프로젝트 리스트 수정
     */
    @PostMapping("/update")
    public String update(
        @RequestParam(value="no", required = true) int no,
		@RequestParam(value="projectNm", required = true) String projectNm,
		@RequestParam(value="contents", required = true) String contents
    ) throws Exception {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("no", no);
        param.put("projectNm", projectNm);
        param.put("contents", contents);
        boardService.updateBoard(param);
        return "redirect:/board/list";
    }

    /**
     * 선택한 프로젝트 리스트 삭제
     */
    @GetMapping("/delete")
    public String delete(Integer no) throws Exception {
        boardService.deleteBoard(no);
        return "redirect:/board/list";
    }

    /**
     * 프로젝트 리스트 등록페이지
     */
    @GetMapping("/writepage")
    public String writePage() throws Exception {
        return "boardInsert.html";
    }

    /**
     * 프로젝트 수정 페이지
     */
    @GetMapping("/updatePage")
    public String updatePage(Integer no, Model model, ProjectAddDto projectAddDto) throws Exception {
        ProjectAddDto board = boardService.getBoardSelectOne(no);
        model.addAttribute("board", board);
        return "boardUpdate.html";
    }

}
