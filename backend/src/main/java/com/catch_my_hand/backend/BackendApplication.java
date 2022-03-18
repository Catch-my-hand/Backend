package com.catch_my_hand.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class BackendApplication {

	@RequestMapping(value = "/" , method = RequestMethod.GET)
	String home() {
		return "Welcome Catchmyhand Backend API";
	}
	public static void main(String[] args){
		SpringApplication.run(BackendApplication.class, args);
		SlackmessageService.send("내손을 잡아 API 서비스 시작");

	}

}
