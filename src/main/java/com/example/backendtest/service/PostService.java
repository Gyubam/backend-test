package com.example.backendtest.service;

import com.example.backendtest.dto.PostAddDto;
import com.example.backendtest.entity.Post;
import com.example.backendtest.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(PostAddDto dto) {

        Post post = new Post(dto.getWriter(),
                dto.getTitle(),
                dto.getContent(),
                dto.getMovieLink(),
                dto.getUploadFileName(),
                dto.getStoreFileName());

        postRepository.save(post);
    }

    public List<Post> findAll(){

        List<Post> postList = postRepository.findAll();
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
}
