package com.loanservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmiRequestDto {
	
	
	private Integer tenureMonths;
	private Double loanAmount;
	private Double interestRate;
}
