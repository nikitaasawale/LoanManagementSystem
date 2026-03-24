package com.customer.controller;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.entity.Login;
import com.customer.entity.User;
import com.customer.exception.UserNotFoundException;
import com.customer.repo.UserRepo;
import com.customer.util.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	public JWTUtil jwtutil;
	@Autowired
	public UserRepo userRepo;
	@RequestMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login request) {
		User user=userRepo.findByUsername(request.getUsername());
		if(user==null) {
			throw new UserNotFoundException("User not found");
		}
		if(!user.getPassword().equals(request.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Password");
		}
		String token=jwtutil.generatetoken(user.getUsername(), user.getPassword());		
		return new ResponseEntity(token,HttpStatus.OK);
	}
	

}
