package com.catch_my_hand.backend.home_sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 성공
@RestController
@RequestMapping(path = "/api/v1")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/find")
    public List<Post> AllPost() {
        return postRepository.findAll();
    }

    








}
