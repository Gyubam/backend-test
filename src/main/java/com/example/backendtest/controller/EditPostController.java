package com.example.backendtest.controller;

import com.example.backendtest.entity.Post;
import com.example.backendtest.file.FileStore;
import com.example.backendtest.file.UploadFile;
import com.example.backendtest.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;

@RequiredArgsConstructor
@RestController
@Slf4j
public class EditPostController {

    private final FileStore fileStore;
    private final PostService postService;

    @PostMapping("/post-edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId,
                           MultipartHttpServletRequest req, Principal principal) throws IOException {

        MultipartFile file = req.getFile("file");
        UploadFile attachFile = fileStore.storeFile(file);

        Post findPost = postService.findById(postId);

        if (!findPost.getWriter().equals(principal.getName())) {
            log.info("post edit error 발생");
            return "error";
        }
//
//        findPost.setPost(principal.getName(),
//                req.getParameter("title"),
//                req.getParameter("content"),
//                req.getParameter("movieLink"),
//                attachFile.getUploadFileName(),
//                attachFile.getStoreFileName());

        postService.editPost(postId,
                principal.getName(),
                req.getParameter("title"),
                req.getParameter("content"),
                req.getParameter("movieLink"),
                attachFile.getUploadFileName(),
                attachFile.getStoreFileName());

        log.info("post edit 완료");

        return "ok";
    }
}
