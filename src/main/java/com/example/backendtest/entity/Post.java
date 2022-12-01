package com.example.backendtest.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Post extends BaseTime{

    @Id @GeneratedValue
    private Long id;

    private String writer;

    private String title;

    @Column(length = 3000)
    private String content;

    private String movieLink;

    private String uploadFileName; // 사용자가 업로드한 이미지 파일명
    private String storeFileName; // 서버 내부 이미지 관리 파일명

    private int deleteYN; // 삭제 여부

    public Post(String writer, String title, String content, String movieLink, String uploadFileName, String storeFileName, int deleteYN) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.movieLink = movieLink;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.deleteYN = deleteYN;
    }

    public void setPost(String writer, String title, String content, String movieLink, String uploadFileName, String storeFileName) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.movieLink = movieLink;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void setDeleteYN() {
        this.deleteYN = 1;
    }


}
