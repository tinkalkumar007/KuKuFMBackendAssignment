package com.kuku.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kuku.entity.Customer;
import com.kuku.service.RegistrationService;

@RestController
@RequestMapping("/api")
public class LoginController {
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping("/login")
	public Map<String,String> loginUser(@RequestBody Map<String,String> req) {
		Customer customer= registrationService.checkLogin(req.get("email"), req.get("password"));
		
		Map<String,String> mp=new HashMap<>();
		if(customer==null) {
			mp.put("message","User not found!");
			return mp;
		}
		mp.put("Id", customer.getCustomerId().toString());
		mp.put("Name", customer.getFirstname());
		mp.put("email", customer.getEmail());
		return mp;
	}
	
	@PostMapping("/register")
	public Map<String,String> registerUser(@RequestBody Map<String,String> req) {
		Customer customer= registrationService.registerCustomer(req);
		Map<String,String> mp=new HashMap<>();
		mp.put("Id", customer.getCustomerId().toString());
		mp.put("Name", customer.getFirstname());
		mp.put("email", customer.getEmail());
		return mp;
	}
}
