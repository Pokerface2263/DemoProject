package com.solvatech.onboarding.demoproject.repository;

import com.solvatech.onboarding.demoproject.dto.PostWithAuthorDTO;
import com.solvatech.onboarding.demoproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p JOIN FETCH p.author")
    List<Post> findAllPostWithAuthor();

    List<Post> findPostsByAuthorId(Long id);
}
