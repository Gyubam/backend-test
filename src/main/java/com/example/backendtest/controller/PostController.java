package com.example.backendtest.controller;

import com.example.backendtest.entity.Post;
import com.example.backendtest.file.FileStore;
import com.example.backendtest.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileStore fileStore;

    @GetMapping("/post")
    public String post(Model model) {

        List<Post> postList = postService.findAllWithNotDelete();

        model.addAttribute("postList", postList);

        return "post";
    }

    @GetMapping("/post-add-form")
    public String addPostForm() {
        return "addPost";
    }

    @GetMapping("/post/{postId}")
    public String postInfo(@PathVariable("postId") Long postId, Model model) {

        Post findPost = postService.findById(postId);


        model.addAttribute("post", findPost);

        return "postInfo";
    }

    @GetMapping("/post-edit-form/{postId}")
    public String editPostForm(@PathVariable("postId") Long postId, Model model) {
        Post findPost = postService.findById(postId);

        model.addAttribute("post", findPost);
        return "editPost";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{postId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable("postId") Long postId)
            throws MalformedURLException {

        Post findPost = postService.findById(postId);
        String storeFileName = findPost.getStoreFileName();
        String uploadFileName = findPost.getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
