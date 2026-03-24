package com.loanservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.loanservice.model.LoanEvent;

@Service
public class LoanEventProducer {
	@Autowired
	public KafkaTemplate<String, Object> kafkatemplate;
	
	public void publishLoanEvent(LoanEvent event) {
		kafkatemplate.send("loan-event", event);
		System.out.println("loan event published to kafka");
		
	}

}
