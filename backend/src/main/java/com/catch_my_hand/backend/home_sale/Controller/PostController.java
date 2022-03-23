package com.catch_my_hand.backend.home_sale.Controller;

import com.catch_my_hand.backend.home_sale.Entity.Outpost;
import com.catch_my_hand.backend.home_sale.Entity.Post;
import com.catch_my_hand.backend.home_sale.Repository.OutpostRepository;
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

    @Autowired
    private OutpostRepository outpostRepository;

    // R : read
    @GetMapping("/find")
    public List<Post> AllPost() {
        return postRepository.findAll();
    }

    // C : Create 완성 후, Time 주입 추가해야됨
    @PostMapping("/inssert")
    public String postinssert(@RequestParam String imageuri, @RequestParam String title,
                              @RequestParam String sell, @RequestParam String content){

        postRepository.save(new Post(imageuri, title, sell, content));
        outpostRepository.save(new Outpost(imageuri, title, sell));

        return "Data saved";
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
