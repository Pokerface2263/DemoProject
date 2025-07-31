package com.solvatech.onboarding.demoproject.controller;

import com.solvatech.onboarding.demoproject.dto.PostDTO;
import com.solvatech.onboarding.demoproject.dto.PostResponseDTO;
import com.solvatech.onboarding.demoproject.dto.PostWithAuthorDTO;
import com.solvatech.onboarding.demoproject.mapper.PostResponseMapper;
import com.solvatech.onboarding.demoproject.model.Post;
import com.solvatech.onboarding.demoproject.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("post")
public class PostController {

    @Autowired
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id){
        return postService.findPostById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostWithAuthorDTO>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}/all")
    public ResponseEntity<List<PostDTO>> getPostsByUserId(@PathVariable  Long id) {
        return ResponseEntity.ok(postService.getAllPostsByUserId(id));
    }

    @PostMapping("/new")
    public ResponseEntity<PostResponseDTO> createPost(@RequestBody PostDTO post) {
        Post newPost = postService.savePost(post);
        return ResponseEntity.ok(PostResponseMapper.mapToDto(newPost));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        return  postService.updatePost(id, postDTO);
    }
}
