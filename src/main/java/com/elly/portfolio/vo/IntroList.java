package com.elly.portfolio.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroList {
    /**
     * 글 번호
     */
    private Integer no;
    /**
     * 프로젝트 제목
     */
    private String projectNm;
    /**
     * 프로젝트 내용
     */
    private String contents;
    /**
     * 프로젝트 작성일
     */
    private Date regDate;
    /**
     * 프로젝트 첨부파일
     */
    private String attachment;
    /**
     * 프로젝트 첨부파일 이름
     */
    private String attachmentNm;
}
