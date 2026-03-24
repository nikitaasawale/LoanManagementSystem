package com.documentservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentservice.entity.Document;
import com.documentservice.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {
	@Autowired
	public DocumentService documentservice;
	
	 public DocumentController(DocumentService documentservice) {
		this.documentservice=documentservice;
	}
	
	@PostMapping("/upload")
	public Document upload(@RequestParam MultipartFile file,
			@RequestParam Long loanId,
			@RequestParam Long customerId,
			@RequestParam String documentType
			) throws Exception{
		return documentservice.upload(file, loanId, customerId, documentType);
	}
	@GetMapping("/getbyLoan/{loanId}")
	public Document getbyLoan(@PathVariable Long loanId){
		return documentservice.getByLoan(loanId);
		 
	}
	@PutMapping("/verify/{loanId}")
	public Document verify(@PathVariable Long loanId) {
		return documentservice.verify(loanId);		
	}
	@PutMapping("/reject/{loanid}")
	public Document reject(@PathVariable Long loanid) {
		return documentservice.reject(loanid);
	}
	
	

}
