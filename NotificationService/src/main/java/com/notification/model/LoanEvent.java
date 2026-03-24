package com.notification.model;

import lombok.Data;

@Data
public class LoanEvent {
	
	private Long loanId;
	private Long customerId;
	private Double amount;
	private String status;

}
