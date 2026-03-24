package com.documentservice.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.standard.DocumentName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.DocumentType;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.documentservice.entity.Document;
import com.documentservice.repository.DocumentRepository;
@Service
public class DocumentService {
	
	private final AmazonS3 amazonS3;
	private final DocumentRepository repository;
	@Value("${aws.bucket-name}")
	private String bucketname;
	@Autowired
	public DocumentService(AmazonS3 amazonS3, DocumentRepository repository) {
		this.amazonS3=amazonS3;
		this.repository=repository;
	}
	
	public Document upload(MultipartFile file,Long loanid, Long customerid, String documentType) throws Exception {
		String key=UUID.randomUUID() + "_" + file.getOriginalFilename(); 
		ObjectMetadata metadata=new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		amazonS3.putObject(bucketname, key, file.getInputStream(), metadata);
		String fileurl=amazonS3.getUrl(bucketname, key).toString();
		Document doc=new Document();
		doc.setLoanId(loanid);
		doc.setCustomerId(customerid);
		doc.setDocumentType(documentType);
		doc.setDocumentName(file.getOriginalFilename());
		doc.setS3key(key);
		doc.setS3url(fileurl);
		doc.setStatus("Uploaded");
		doc.setUploadDate(LocalDateTime.now());
		return repository.save(doc);
	}
	
	public Document getByLoan(Long loanid){
	    return repository.findByLoanIdAndDeletedFalse(loanid);
	}
	
	public Document verify(Long id) {
		Document doc=repository.findById(id).orElseThrow(() -> new RuntimeException("Document not Found of id:" + id));
		doc.setStatus("Verified");
		return repository.save(doc);
	
	}
	public Document reject(Long id) {
		Document doc=repository.findById(id).orElseThrow(() -> new RuntimeException("Document not Found of id:"+ id));
		doc.setStatus("Rejected");
		return repository.save(doc);
	}
	public void delete(Long id) {
		Document doc=repository.findById(id).orElseThrow(() -> new RuntimeException("Document not Found of id:"+ id));
		doc.setDeleted(true);
		 repository.save(doc);
	}

}
