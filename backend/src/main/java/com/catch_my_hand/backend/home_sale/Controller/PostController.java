package com.catch_my_hand.backend.home_sale.Controller;

import com.catch_my_hand.backend.home_sale.Entity.Post;
import com.catch_my_hand.backend.home_sale.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// 성공
@RestController
@RequestMapping(path = "/api/v1")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    // R : read
    @GetMapping("/find")
    public List<Post> AllPost() {
        return postRepository.findAll();
    }

    // C : Create
    @PostMapping("/inssert")
    public Post postinssert(@RequestParam String imageuri, @RequestParam String title,
                            @RequestParam String sell, @RequestParam String content){

        return postRepository.save(new Post(imageuri, title, sell, content));
    }

    // U : update 날짜는 자동지정인데 이건 어떻게할까 고민해보자
    @PostMapping("/posting/{id}")
    public Post update(@PathVariable Integer id, @RequestParam String imageuri, @RequestParam String title,
                       @RequestParam String sell, @RequestParam String content) {

        Optional<Post> post = postRepository.findById(id);
        post.get().setTitle(title);
        post.get().setImageuri(imageuri);
        post.get().setSell(sell);
        post.get().setContent(content);
        return postRepository.save(post.get());
    }

    // D : Delete
    @DeleteMapping("/posting/{id}/delete")
    public void deletePosting (@PathVariable Integer id) {
        postRepository.deleteById(id);
    }


}
