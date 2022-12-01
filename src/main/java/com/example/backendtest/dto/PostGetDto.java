package com.example.backendtest.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
public class PostGetDto {

    private Long id;

    private String writer;

    private String title;

    private String content;

    private String movieLink;

    private String uploadFileName; // 사용자가 업로드한 이미지 파일명
    private String storeFileName; // 서버 내부 이미지 관리 파일명


    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public PostGetDto(Long id, String writer, String title, String content, String movieLink, String uploadFileName, String storeFileName, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.movieLink = movieLink;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}

