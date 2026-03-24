package com.documentservice.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long documentId;
	private Long loanId;
	private Long customerId;
	private String documentType;
	private String documentName;
	private String S3key;
	private String S3url;
	private String status;
	private LocalDateTime uploadDate;
	private boolean deleted=false;
}
