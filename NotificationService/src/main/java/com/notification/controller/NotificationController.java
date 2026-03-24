package com.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.notification.consumer.LoanEventConsumer;
import com.notification.model.LoanEvent;
import com.notification.service.NotificationService;

@RestController
@RequestMapping("/mail")
public class NotificationController {
	@Autowired
	public LoanEventConsumer loaneventconsumer;
	@Autowired
	public NotificationService notificationservice;
	@PostMapping("/send")
	public ResponseEntity<String> sendMail(@RequestBody LoanEvent event){
		loaneventconsumer.consumeLoanEvent(event);
		return new ResponseEntity<>("Mail sent Successfully!!",HttpStatus.OK);
	}
	@PostMapping("/done")
	public ResponseEntity<String> mailsending(@RequestBody String message){
		notificationservice.sendMail(message);
		return new ResponseEntity<>("Mail sent Successfully!!",HttpStatus.OK);
		
	}


}
