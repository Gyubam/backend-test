package com.example.backendtest.controller;

import com.example.backendtest.dto.PostAddDto;
import com.example.backendtest.entity.Post;
import com.example.backendtest.file.FileStore;
import com.example.backendtest.file.UploadFile;
import com.example.backendtest.repository.PostRepository;
import com.example.backendtest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AddPostController {

    private final FileStore fileStore;
    private final PostService postService;

    @PostMapping("/post-add")
    public String addPost(MultipartHttpServletRequest req, Principal principal) throws IOException {

        MultipartFile file = req.getFile("file");
        UploadFile attachFile = fileStore.storeFile(file);

        PostAddDto dto = new PostAddDto(
                principal.getName(),
                req.getParameter("title"),
                req.getParameter("content"),
                req.getParameter("movieLink"),
                attachFile.getUploadFileName(),
                attachFile.getStoreFileName()
        );

        postService.savePost(dto);
        return dto.getWriter();
    }
}
