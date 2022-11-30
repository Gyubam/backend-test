package com.example.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAddDto {

    private String writer;

    private String title;

    private String content;

    private String movieLink;

    private String uploadFileName; // 사용자가 업로드한 이미지 파일명
    private String storeFileName; // 서버 내부 이미지 관리 파일명

}
