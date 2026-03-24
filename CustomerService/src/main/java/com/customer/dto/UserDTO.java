package com.customer.dto;

import com.customer.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class UserDTO {
	
	private int id;
	
	private String fname;
	
	private String lname;
	
	private String address;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String mobilenumber;
	
	private String dob;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String pincode;
	
	private LoginDTO logindto;
	
	

}
