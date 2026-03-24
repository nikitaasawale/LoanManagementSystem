package com.loanservice.service;

import java.util.List;

import com.loanservice.dto.EmiRequestDto;
import com.loanservice.entity.Loan;

public interface LoanService {
	
	public Loan applyforLoan(Loan loan);
	public Loan getLoanById(Long loanId);
	public Loan getCustomerLoans(Long customerId);
	public List<Loan> getLoanByStatus(String status);
	public Double calculateEmi(EmiRequestDto emidto);
	public Loan approveLoan(Long loanId);
	public Loan rejectLoan(Long loanId);
	public Loan softDelete(Long loanId);

}
