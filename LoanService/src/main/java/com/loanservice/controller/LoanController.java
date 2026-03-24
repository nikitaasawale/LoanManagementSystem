package com.loanservice.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loanservice.dto.EmiRequestDto;
import com.loanservice.entity.Loan;
import com.loanservice.service.LoanService;

@RestController
@RequestMapping("/loan/v1")
public class LoanController {
	@Autowired
	public LoanService loanservice;
	
	@PostMapping("/apply")
	public ResponseEntity<?> applyLoan(@RequestBody Loan loan) {
		loanservice.applyforLoan(loan);
		return new ResponseEntity("Loan applied successfully!!",HttpStatus.OK);
		
	}
	@GetMapping("/getloanbyId/{loanId}")
	public ResponseEntity<Loan> getLoanById(@PathVariable("loanId") Long loanId) {
		Loan loan=loanservice.getLoanById(loanId);
		return new ResponseEntity<Loan>(loan,HttpStatus.FOUND);
		
	}
	@GetMapping("/getcustomerloan/{customerId}")
	public ResponseEntity<Loan> getCustomerLoans(@PathVariable("customerId") Long customerId) {
		Loan loan=loanservice.getCustomerLoans(customerId);
		return new ResponseEntity<Loan>(loan,HttpStatus.FOUND);
	}
	@GetMapping("/getbystatus/{status}")
	public ResponseEntity<List<Loan>> getByStatus(@PathVariable String status) {
		List<Loan> list=loanservice.getLoanByStatus(status);
		return new ResponseEntity<List<Loan>>(list,HttpStatus.FOUND);
	}
	@PostMapping("/calculateEmi")
	public ResponseEntity<Double> calculateEmi(@RequestBody EmiRequestDto request) {
		 //Double emi=loanservice.calculateEmi(request);
		 return ResponseEntity.ok(loanservice.calculateEmi(request));
		
	}
	@PostMapping("/approve/{loanId}")
	public ResponseEntity<Loan> approveLoan(@PathVariable("loanId") Long loanid) {
		Loan loan=loanservice.approveLoan(loanid);
		return new ResponseEntity<Loan>(loan,HttpStatus.OK);
		
	}
	@PostMapping("/reject/{loanId}")
	public ResponseEntity<Loan> rejectLoan(@PathVariable("loanId") Long loanid){
		Loan loan=loanservice.rejectLoan(loanid);
		return new ResponseEntity<Loan>(loan,HttpStatus.OK);
		
	}
	@PostMapping("/softdelete/{loanId}")
	public ResponseEntity<Loan> softDelete(@PathVariable("loanId") Long loanId){
		Loan loan=loanservice.softDelete(loanId);
		return new ResponseEntity<Loan>(loan,HttpStatus.OK);
	}

}
