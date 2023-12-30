package com.zproject.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zproject.demo.entity.User;
import com.zproject.demo.repository.UserRepository;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//private List<User> store = new ArrayList<>();

	//public UserService() {
		
	//	store.add(new User(UUID.randomUUID().toString(),"Ragib Hasan","ragibhasan006@gmail.com"));
		
	//	store.add(new User(UUID.randomUUID().toString(),"Abid Hasan","abidhasan006@gmail.com"));
		
	//	store.add(new User(UUID.randomUUID().toString(),"Ragib","ragib006@gmail.com"));
		
//	}
	
	
	
	public List<User> getUsers(){
		
		return userRepository.findAll();
		
		
	}
	
	
	public User createUser(User user) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
		
	}
	
	

	
	

}
