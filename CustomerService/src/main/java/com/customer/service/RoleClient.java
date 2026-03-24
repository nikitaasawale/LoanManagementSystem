package com.customer.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.Role;

@FeignClient(name = "ADMINSERVICE")
public interface RoleClient {
	
	@GetMapping("/getrole/{rolename}")
	Role getRole(@PathVariable("rolename") String rolename);

}
