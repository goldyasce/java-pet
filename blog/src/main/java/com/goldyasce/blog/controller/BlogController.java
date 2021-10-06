package com.goldyasce.blog.controller;

import com.goldyasce.blog.entity.Post;
import com.goldyasce.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class BlogController {
    private final PostRepository postRepository;

    @Autowired
    public BlogController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("posts", postRepository.findAll());

        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String tag,
                      @RequestParam String text,
                      Map<String, Object> model) {
        Post newPost = new Post(tag, text);

        postRepository.save(newPost);

        model.put("posts", postRepository.findAll());

        return "main";
    }

    @PostMapping("search")
    public String search(@RequestParam String tag,
                         Map<String, Object> model) {
        List<Post> posts;

        if (tag != null && !tag.isEmpty()) {
            posts = postRepository.findByTag(tag);
            if (posts.isEmpty()) {
                posts = postRepository.findAll();
            }
        } else {
            posts = postRepository.findAll();
        }

        model.put("posts", posts);

        return "main";
    }
}
