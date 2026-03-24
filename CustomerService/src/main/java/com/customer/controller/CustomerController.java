package com.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.dto.ChangePasswordDTO;
import com.customer.dto.ForgotPasswordDTO;
import com.customer.entity.User;
import com.customer.entity.UserResponse;
import com.customer.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserResponse> registerUserInfo(@RequestBody User user){
		log.info("In Customer controller start");
		UserResponse userResponse=customerservice.registerUserInfo(user);
		log.info("In Customer controller end");
		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getUser/{userId}")
	public ResponseEntity<?> getUserDataByEmail(@PathVariable("userId") String email){
		System.out.println("In Customer controller getting user start");
		ResponseEntity<?> responseEntity= customerservice.getUserDataByEmail(email);
		System.out.println("In Customer controller getting user end");
		return responseEntity;
	}
	@GetMapping(value = "/getUserbyId/{id}")
	public ResponseEntity<?> getUserDataById(@PathVariable("id") int id){
		System.out.println("In Customer controller getting user by Id start");
		ResponseEntity<?> responseEntity=customerservice.getUserDataById(id);
        System.out.println("In Customer controller getting user by Id end");
		return responseEntity;
	}
	@GetMapping(value = "/getUserbyUsername/{username}")
	public ResponseEntity<?> getUserDataByUsername(@PathVariable("username") String username){
		System.out.println("In Customer controller getting user by Username start");
		ResponseEntity<?> responseEntity= customerservice.getUserDataByUsername(username);
		System.out.println("In Customer controller getting user by Username end");
		return responseEntity;
	}
	@PutMapping(value = "/updateUserbyUsername/{username}")
	public ResponseEntity<User> updateUserByUsername(@PathVariable("username") String username,@RequestBody User user){
		System.out.println("In Customer controller updating user by Username start");
		ResponseEntity<User> responseEntity=customerservice.updateUserById(username,user);
		System.out.println("In Customer controller updating user by Username end");
		return responseEntity;
	}
	@DeleteMapping(value = "/softdeleteUserbyId/{id}")
	public ResponseEntity<?> softdeleteById(@PathVariable("id") int id){
		System.out.println("In Customer controller soft deleting user by Username start");
		ResponseEntity<?> responseEntity =customerservice.softdeleteUserById(id);
		System.out.println("In Customer controller soft deleting user by Username end");
		return responseEntity;
	}
	@PutMapping("/forgotpassword")
	public ResponseEntity<UserResponse> forgotPassword(@RequestBody ForgotPasswordDTO forgot){
		System.out.println("In Customer controller forgotpassword by Username and Email start");
		ResponseEntity<UserResponse> response=customerservice.forgotpassword(forgot);
		System.out.println("In Customer controller forgotpassword by Username and Email end");
		return response;	
	}
	@PutMapping("/changepassword")
	public ResponseEntity<UserResponse> changePassword(@RequestBody ChangePasswordDTO change){
		System.out.println("In Customer controller change password by Username and Email start");
		ResponseEntity<UserResponse> response=customerservice.changePassword(change);
		System.out.println("In Customer controller change password by Username and Email end");
		return response;
	}
	@GetMapping("/getallusernames")
	public ResponseEntity<List<String>> getAllUsernames(){
		List<String> usernames=customerservice.getAllUsernames();
		return new ResponseEntity<List<String>>(usernames,HttpStatus.OK);
	}
	@PostMapping("/assignrole/{rolename}/{username}")
	public ResponseEntity<?> assignRoleToUser(@PathVariable("rolename") String rolename,@PathVariable("username") String username){
		log.info("In Customer controller assigning role by rolename start");
		 return customerservice.assignRoletoUser(username, rolename);	
	}

}

