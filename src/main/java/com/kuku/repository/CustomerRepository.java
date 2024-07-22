package com.kuku.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kuku.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
	@Query("SELECT c from Customer c WHERE c.email=:email AND c.password=:password")
	Optional<Customer> findByUsernameAndPassword(@Param("email") String email,@Param("password") String password);
}
