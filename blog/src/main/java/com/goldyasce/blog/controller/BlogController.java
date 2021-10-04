package com.goldyasce.blog.controller;

import com.goldyasce.blog.entity.Post;
import com.goldyasce.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BlogController {
    private final PostRepository postRepository;

    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
                        Map<String, Object> model) {
        model.put("name", name);

        return "hello";
    }

    @GetMapping("/posts")
    public String getPosts(Map<String, Object> model) {
        model.put("posts", postRepository.findAll());

        return "main";
    }

    @GetMapping
    public String main() {
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String tag,
                      @RequestParam String text,
                      Map<String, Object> model) {
        Post newPost = new Post(tag, text);

        postRepository.save(newPost);

        model.put("posts", postRepository.findAll());

        return "main";
    }
}
