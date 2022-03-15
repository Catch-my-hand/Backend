package com.catch_my_hand.backend.home_sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// 성공
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/api/v1/find")
    public List<Post> AllPost() {
        return postRepository.findAll();
    }

    @PostMapping("/api/v1/posting")
    public Post create(@RequestBody Map<String,String> body) {
        String title = body.get("title");
        String sell = body.get("sell");
        String content = body.get("content");

    }



}
