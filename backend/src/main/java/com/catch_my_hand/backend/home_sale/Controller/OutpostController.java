package com.catch_my_hand.backend.home_sale.Controller;


import com.catch_my_hand.backend.home_sale.Entity.Outpost;
import com.catch_my_hand.backend.home_sale.Repository.OutpostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class OutpostController {

    @Autowired
    private OutpostRepository outpostRepository;


    @GetMapping("/outpost")
    public List<Outpost> Postview() {
        return outpostRepository.findAll();
    }


}
