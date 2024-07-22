package com.kuku.service;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.entity.Customer;
import com.kuku.repository.CustomerRepository;

@Service
public class RegistrationService {
	@Autowired
	CustomerRepository customerRepo;
	public Customer registerCustomer(Map<String,String> req) {
		Customer customer=new Customer();
		customer.setFirstname(req.get("firstName"));
		customer.setLastname(req.get("lastName"));
		customer.setAddressLine1(req.get("addressLine1"));
		customer.setAddressLine2(req.get("addressLine2"));
		customer.setCity(req.get("city"));
		customer.setCountry(req.get("country"));
		customer.setEmail(req.get("email"));
		customer.setState(req.get("state"));
		customer.setPassword(req.get("password"));
		customer.setPhone(req.get("phone"));
		customer.setZipcode(req.get("zipCode"));
		customer.setRegisterDate(new Date());
		return customerRepo.save(customer);
	}
	public Customer checkLogin(String email,String password) {
		Optional<Customer> result= customerRepo.findByUsernameAndPassword(email, password);
		if(result.isPresent()){
			return result.get();
		}
		return null;
	}
}

