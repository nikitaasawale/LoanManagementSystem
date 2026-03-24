package com.customer.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordDTO {
	
	private String username;
	private String email;
	private String newpassword;
	private String confirmpassword;

}
