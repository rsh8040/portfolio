package com.elly.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectAddDto {

    /**
     * 프로젝트 넘버
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
