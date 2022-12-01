package com.example.backendtest.controller;

import com.example.backendtest.entity.Post;
import com.example.backendtest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DeletePostController {

    private final PostService postService;

    @PostMapping("/post-delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId, Principal principal) {

        Post findPost = postService.findById(postId);
        log.info("deletePost controller 실행");
        if (!findPost.getWriter().equals(principal.getName())) {
            log.info("post delete 인증 실패");
            return "error";
        }

        postService.deletePost(postId);

        return "ok";
    }
}
