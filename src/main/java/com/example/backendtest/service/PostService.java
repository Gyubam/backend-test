package com.example.backendtest.service;

import com.example.backendtest.dto.PostAddDto;
import com.example.backendtest.entity.Post;
import com.example.backendtest.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostAddDto dto) {

        Post post = new Post(dto.getWriter(),
                dto.getTitle(),
                dto.getContent(),
                dto.getMovieLink(),
                dto.getUploadFileName(),
                dto.getStoreFileName(),
                0);

        postRepository.save(post);
    }

    public List<Post> findAll(){
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public List<Post> findAllWithNotDelete(){
        List<Post> postList = postRepository.findAllWithNotDelete();
        return postList;
    }

    public Post findById(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            return post.get();
        }
        return null;
    }

    @Transactional
    public void editPost(Long postId,
                         String name,
                         String title,
                         String content,
                         String movieLink,
                         String uploadFileName,
                         String storeFileName) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post findPost = optionalPost.get();

            findPost.setPost(name, title, content, movieLink, uploadFileName, storeFileName);
        }

        return;
    }

    @Transactional
    public void deletePost(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post findPost = optionalPost.get();
            findPost.setDeleteYN();
            log.info("post delete 실행, 게시글 번호={}", postId);
        }

        return;
    }
}
