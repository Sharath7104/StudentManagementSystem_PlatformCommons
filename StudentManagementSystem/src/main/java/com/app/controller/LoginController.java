package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService customerService;

	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth){
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		 User customer= customerService.getUserDetailsByEmail(auth.getName());
		 
		 return new ResponseEntity<>(customer.getName()+" Logged In Successfully", HttpStatus.ACCEPTED);	
	}
}
