package com.goldyasce.blog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tag;

    private String text;

    public Post() {

    }

    public Post(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }
}
