package com.customer.exception;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
	private String message;
	private int statuscode;
	private LocalDateTime localdatetime;

}
