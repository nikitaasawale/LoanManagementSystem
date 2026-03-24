package com.customer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.ChangePasswordDTO;
import com.customer.dto.ForgotPasswordDTO;
import com.customer.dto.Role;
import com.customer.entity.User;
import com.customer.entity.UserResponse;

public interface CustomerService {
	
	UserResponse registerUserInfo(User user);
	ResponseEntity<?> getUserDataByEmail(String email);
	ResponseEntity<?> getUserDataById(int id);
	ResponseEntity<?> getUserDataByUsername(String username);
	ResponseEntity<User> updateUserById(String username,User user);
	ResponseEntity<?> softdeleteUserById(int id);
	ResponseEntity<UserResponse> forgotpassword(ForgotPasswordDTO forgot);
	ResponseEntity<UserResponse> changePassword(ChangePasswordDTO change);
	ResponseEntity<?> assignRoletoUser(String username,String rolename);
	List<String> getAllUsernames();

}
