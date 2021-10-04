package com.goldyasce.blog.repo;

import com.goldyasce.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTag(String tag);
}
