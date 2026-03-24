package com.customer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.customer.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> { 
	@Query(value = "select username from login ",nativeQuery = true)
	List<String> findByAllUsernames();
	

}
