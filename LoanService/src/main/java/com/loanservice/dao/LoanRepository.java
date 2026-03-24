package com.loanservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loanservice.entity.Loan;
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{
	
	public List<Loan> findByStatus(String status);
	public Loan findByCustomerId(Long customerId);
	
	
	

}
