package com.example.backendtest.service;

import com.example.backendtest.dto.PostAddDto;
import com.example.backendtest.dto.PostGetDto;
import com.example.backendtest.dto.PostSearchCondition;
import com.example.backendtest.dto.QPostGetDto;
import com.example.backendtest.entity.Post;

import com.example.backendtest.entity.QPost;
import com.example.backendtest.repository.PostRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.example.backendtest.entity.QPost.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final JPAQueryFactory queryFactory;


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

    public List<Post> findAll() {
        List<Post> postList = postRepository.findAll();
        return postList;
    }

    public List<PostGetDto> findAllWithNotDelete() {
        List<PostGetDto> postList = postRepository.findAllWithNotDelete();
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

    public List<PostGetDto> search(PostSearchCondition condition) {
        return queryFactory
                .select(new QPostGetDto(
                        post.id,
                        post.writer,
                        post.title,
                        post.content,
                        post.movieLink,
                        post.uploadFileName,
                        post.storeFileName,
                        post.createdDate,
                        post.modifiedDate))
                .from(post)
                .where(containTitle(condition.getTitle()),
                        containContent(condition.getContent()),
                        post.deleteYN.eq(0))
                .fetch();
    }

    private BooleanExpression containContent(String content) {
        return StringUtils.hasText(content) ? post.content.contains(content) : null;
    }

    private BooleanExpression containTitle(String title) {
        return StringUtils.hasText(title) ? post.title.contains(title) : null;
    }


}
