package com.documentservice.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.documentservice.entity.Document;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
	
	public Document findByLoanIdAndDeletedFalse(Long loanid);
	

}
