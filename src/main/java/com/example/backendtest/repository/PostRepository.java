package com.example.backendtest.repository;

import com.example.backendtest.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.deleteYN = 0")
    List<Post> findAllWithNotDelete();
}
