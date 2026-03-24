package com.loanservice.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loanservice.dao.LoanRepository;
import com.loanservice.dto.EmiRequestDto;
import com.loanservice.entity.Loan;
import com.loanservice.model.LoanEvent;
import com.loanservice.producer.LoanEventProducer;
@Service
public class LoanServiceImpl implements LoanService {
	@Autowired
	public LoanRepository loanrepo;
	@Autowired
	public LoanEventProducer loaneventProducer;

	@Override
	public Loan applyforLoan(Loan loan) {
		loan.setStatus("Pending");
		loan.setAppliedDate(LocalDateTime.now());
		loan.setDeleted(false);
		Double emi=calculateEmi(
				new EmiRequestDto(
				loan.getTenuremonths(),		
				loan.getInterestRate(),
				loan.getLoanAmount()
				));
		loan.setEmiAmount(emi);
		return loanrepo.save(loan);
	}

	@Override
	public Loan getLoanById(Long loanId) {
		Loan loan=loanrepo.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
		return loan;
	}

	@Override
	public Loan getCustomerLoans(Long customerId) {
		Loan loan=loanrepo.findByCustomerId(customerId);
		return loan;
	}

	@Override
	public List<Loan> getLoanByStatus(String status) {
		List<Loan> list=loanrepo.findByStatus(status);
		return list;
	}

	@Override
	public Double calculateEmi(EmiRequestDto emidto) {
		Double P=emidto.getLoanAmount();
		Double R=emidto.getInterestRate() / (12 * 100);
		Integer N=emidto.getTenureMonths();
		return(P * R * Math.pow(1+ R, N))/
				     (Math.pow(1+R, N)-1);
		
	}

	@Override
	public Loan approveLoan(Long loanId) {
		Loan loan=getLoanById(loanId);
		loan.setApprovalDate(LocalDateTime.now());
		loan.setStatus("Approved");
		Loan savedloan= loanrepo.save(loan);
		LoanEvent event=new LoanEvent(savedloan.getLoanId(),
				savedloan.getCustomerId(),
				savedloan.getLoanAmount(),
				"Approved");
		;
		loaneventProducer.publishLoanEvent(event);
		return savedloan;
		
	}

	@Override
	public Loan rejectLoan(Long loanId) {
		Loan loan=getLoanById(loanId);
		loan.setStatus("Rejected");
		Loan savedloan= loanrepo.save(loan);
		LoanEvent event=new LoanEvent(savedloan.getLoanId(),
				savedloan.getCustomerId(),
				savedloan.getLoanAmount(),
				"Rejected");
		;
		loaneventProducer.publishLoanEvent(event);
		return savedloan;
		
	}

	@Override
	public Loan softDelete(Long loanId) {
		Loan loan=getLoanById(loanId);
		loan.setDeleted(true);
		return loanrepo.save(loan);
		
	}

}
