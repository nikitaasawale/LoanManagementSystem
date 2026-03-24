package com.loanservice.entity;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long loanId;
	private Long customerId;
	private Double loanAmount;
	private Integer tenuremonths; 
	private Double interestRate;
	private Double emiAmount;
	private String loanType;
	private String status;
	private LocalDateTime appliedDate;
	private LocalDateTime approvalDate;
	@Column(nullable = false)
	private Boolean deleted=false;
	
	
	

}
