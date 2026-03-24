package com.customer.dto;

import com.customer.entity.Login;
import com.customer.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
	
	private int id;
	private String username;
	private String email;
	

}
