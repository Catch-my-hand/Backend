package com.catch_my_hand.backend.home_sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 성공
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/api/v1/posting")
    public List<Post> AllPost() {
        return postRepository.findAll();
    }


}
