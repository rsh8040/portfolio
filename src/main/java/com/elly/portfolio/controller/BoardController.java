package com.elly.portfolio.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.elly.portfolio.dto.ProjectAddDto;
import com.elly.portfolio.service.BoardService;
import com.elly.portfolio.vo.IntroList;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;

    /**
     * 메인페이지
     */
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

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
        model.addAttribute("board", board);
        return "boardDetail.html";
    }

    /**
     * 프로젝트 리스트 등록
     */
    @PostMapping(value = "/write", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String write(
        @RequestParam(value="projectNm", required = true) String projectNm,
        @RequestParam(value="contents", required = true) String contents,
        @RequestParam(value = "attachment") MultipartFile attachment
        ) throws Exception {
            String originalFileName = attachment.getOriginalFilename();
            String now  = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
            String newFileName = "attach_"+now;
            String extention = FilenameUtils.getExtension(originalFileName);
            String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
            String basePath = rootPath + "\\"+ "temp";
            String projectPath = basePath;
            if(!attachment.isEmpty()){
                try{
                    InputStream inputStream = attachment.getInputStream();
                    File saveFile = new File(projectPath,newFileName+"."+extention);
                    attachment.transferTo(saveFile);
                    inputStream.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            HashMap<String,Object> param = new HashMap<String, Object>();
            param.put("projectNm", projectNm);
            param.put("contents", contents);
            if(null != attachment) {
                param.put("attachment", "/upload/" + newFileName+"."+extention);
                param.put("attachmentNm",originalFileName);
            }
        boardService.insertBoard(param);
        return "redirect:/board/list";
    }

    /**
     * 선택한 프로젝트 리스트 수정
     */
    @PostMapping(value = "/update", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public String update(
        @RequestParam(value="no", required = true) int no,
		@RequestParam(value="projectNm", required = true) String projectNm,
		@RequestParam(value="contents", required = true) String contents,
        @RequestParam(value = "attachment") MultipartFile attachment
    ) throws Exception {
        String originalFileName = attachment.getOriginalFilename();
        String now  = new SimpleDateFormat("yyyyMMddHmsS").format(new Date());
        String newFileName = "attach_"+now;
        String extention = FilenameUtils.getExtension(originalFileName);
        String rootPath = FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String basePath = rootPath + "\\"+ "temp";
        String projectPath = basePath;
        if(!attachment.isEmpty()){
            try{
                InputStream inputStream = attachment.getInputStream();
                File saveFile = new File(projectPath,newFileName+"."+extention);
                attachment.transferTo(saveFile);
                
                inputStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("no", no);
        param.put("projectNm", projectNm);
        param.put("contents", contents);
        if(null != attachment) {
            param.put("attachment", "/upload/" + newFileName+"."+extention);
            param.put("attachmentNm", attachment.getOriginalFilename());
        }
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

    /**
     * skill 페이지
     */
    @GetMapping("/skill")
    public String skill() {
        return "skill.html";
    }

}
