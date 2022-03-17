package com.catch_my_hand.backend.User;

import com.catch_my_hand.backend.home_sale.Entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/create")
    public User createuser(@RequestParam String name, @RequestParam String userid,
                            @RequestParam String password, @RequestParam String nicname){

        return userRepository.save(new User(name, userid, password, nicname));
    }

}
