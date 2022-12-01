package com.example.backendtest.repository;

import com.example.backendtest.dto.PostGetDto;
import com.example.backendtest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new com.example.backendtest.dto.PostGetDto(p.id, p.writer, p.title, p.content, p.movieLink, p.uploadFileName, p.storeFileName, p.createdDate, p.modifiedDate)" +
            " from Post p where p.deleteYN = 0")
    List<PostGetDto> findAllWithNotDelete();
}
