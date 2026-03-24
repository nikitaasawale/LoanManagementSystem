package com.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.customer.dto.LoginDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Login {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	private String email;
	
	@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

//	@Override
//	public String toString() {
//		return "Login [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
//	}
	public LoginDTO convertLogintoLoginDto(Login login) {
		return LoginDTO.builder().id(login.getId()).username(login.getUsername()).email(login.getEmail()).build();
		
	}
	

}
