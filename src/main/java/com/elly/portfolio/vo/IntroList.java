package com.elly.portfolio.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroList {
    /**
     * 글 번호
     */
    private int no;
    /**
     * 프로젝트 제목
     */
    private String projectNm;
    /**
     * 프로젝트 내용
     */
    private String contents;
}
