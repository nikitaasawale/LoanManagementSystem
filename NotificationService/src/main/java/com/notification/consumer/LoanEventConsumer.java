package com.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.notification.model.LoanEvent;
import com.notification.service.NotificationService;
@Component
public class LoanEventConsumer {
	@Autowired
	public NotificationService notificationservice;
	@KafkaListener(topics = "loan-event",groupId = "notification-group")
	public void consumeLoanEvent(LoanEvent event) {
		System.out.println("Loan Event received");
		System.out.println("Notification Service -> LoanId "+ event.getLoanId());
		System.out.println("Notification Service -> LoanId "+ event.getCustomerId());
		System.out.println("Notification Service -> LoanId "+ event.getAmount());
		System.out.println("Notification Service -> LoanId "+ event.getStatus());
		
		String message=" Loan "+ event.getStatus() +
				" for loanId " + event.getLoanId();
		notificationservice.sendMail(message);
	}


}
