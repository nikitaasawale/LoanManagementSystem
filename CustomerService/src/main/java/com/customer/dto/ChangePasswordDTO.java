package com.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordDTO {
	private String username;
	private String email;
	private String oldpassword;
	private String newpassword;
	private String confirmpassword;

}
