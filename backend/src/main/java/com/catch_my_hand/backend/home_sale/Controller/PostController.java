package com.catch_my_hand.backend.home_sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping("/inssert")
    public Post postinssert(@RequestParam String imageuri, @RequestParam String title,
                            @RequestParam String sell, @RequestParam String content){

        return postRepository.save(new Post(imageuri, title, sell, content));
    }




}
