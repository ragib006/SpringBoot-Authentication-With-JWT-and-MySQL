package com.zproject.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zproject.demo.entity.User;
import com.zproject.demo.service.UserService;



@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/user")
	public List<User> getuser() {
		
		//return "hello";
		
		//System.out.println("gattinguser");
		
		return this.userService.getUsers();
	}
	
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		
		return principal.getName();
		
	}
	
	
	

	

}
