package com.solvatech.onboarding.demoproject.service;

import com.solvatech.onboarding.demoproject.dto.PostDTO;
import com.solvatech.onboarding.demoproject.dto.PostWithAuthorDTO;
import com.solvatech.onboarding.demoproject.dto.UserResponseDTO;
import com.solvatech.onboarding.demoproject.mapper.PostMapper;
import com.solvatech.onboarding.demoproject.mapper.UserMapper;
import com.solvatech.onboarding.demoproject.model.Post;
import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.repository.PostRepository;
import com.solvatech.onboarding.demoproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    public ResponseEntity<PostDTO> findPostById(Long id) {
        return postRepository.findById(id)
                .map(PostMapper::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    public ResponseEntity<List<PostDTO>> findAllPosts() {
//        List<PostDTO> posts = postRepository.findAll()
//                .stream()
//                .map(PostMapper::mapToDto)
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(posts);
//    }

    public List<PostWithAuthorDTO> getAllPosts() {
        List<PostWithAuthorDTO> postResults = postRepository.findAllPostWithAuthor()
                .stream()
                .map(PostMapper::mapToDtoWithAuthor)
                .collect(Collectors.toList());
        return postResults;
    }

    public List<PostDTO> getAllPostsByUserId(Long id) {
        return postRepository.findPostsByAuthorId(id)
                .stream()
                .map(PostMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Post savePost(PostDTO post) {
        User author = userRepository.findById(post.getAuthorId())
                .orElse(null);
        Post newPost = new Post();
        newPost.setContent(post.getContent());
        newPost.setImageUrl(post.getImageUrl());
        newPost.setCreatedAt(post.getCreatedAt());
        newPost.setAuthor(author);

        postRepository.save(newPost);
        return newPost;
    }

    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }

    public ResponseEntity<PostDTO> updatePost(Long id, PostDTO updatedPost) {
      return postRepository.findById(id)
              .map(currentPost -> {
                  currentPost.setContent(updatedPost.getContent());
                  currentPost.setImageUrl(updatedPost.getImageUrl());

                  return PostMapper.mapToDto(postRepository.save(currentPost));
              })
              .map(ResponseEntity::ok)
              .orElse(ResponseEntity.notFound().build());
    }
}
