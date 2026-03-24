package com.customer.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.customer.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	@NotNull(message ="Name cannot be null")
	private String fname;
	@Column(nullable = false)
	@NotNull(message ="LastName cannot be null")
	private String lname;
	@Column(nullable = false)
	@NotNull(message ="Address cannot be null")
	private String address;
	@Column(nullable = false)
	@NotNull(message ="UserName cannot be null")
	@NotBlank
	private String username;
	@Column(nullable = false)
	@NotNull(message ="Email cannot be null")
	private String email;
	@Column(nullable = false)
	@NotNull(message ="Password cannot be null")
	private String password;
	private String mobilenumber;
	private String dob;
	private String createdby;
	private String createdDate;
	private String updatedby;
	private String updatedDate;
	private String country;
	private String state;
	private String city;
	private String pincode;
	private boolean status;
	private String rolename;
	@JsonManagedReference
	@OneToOne(cascade = CascadeType.ALL)
	private Login login;
	
	public UserDTO convertusertouserdto(User user) {
		return UserDTO.builder().id(user.getId()).fname(user.getFname()).lname(user.getLname()).address(user.getAddress()).mobilenumber(user.getAddress())
				.dob(user.getDob()).country(user.getCountry()).state(user.getState()).city(user.getCity()).pincode(user.getPincode())
				.logindto(login.convertLogintoLoginDto(user.getLogin())).build();
	}
	

}
