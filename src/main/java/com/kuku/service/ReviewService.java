package com.kuku.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.entity.Book;
import com.kuku.entity.Customer;
import com.kuku.entity.Review;
import com.kuku.repository.BookRepository;
import com.kuku.repository.CustomerRepository;
import com.kuku.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	
	public Map<String,String> writeReview(Map<String, String> req,Integer bookId,Integer customerId) {
		Review review=new Review();
		review.setComment(req.get("comment"));
		review.setHeadline(req.get("headline"));
		review.setRating(Float.parseFloat(req.get("rating")));
		review.setReviewTime(new Date());
		Optional<Book> result=bookRepo.findById(bookId);
		Map<String,String> mp=new HashMap<>();
		Book book=null;
		if(result.isPresent()) {
			book=result.get();
		}
		else {
			mp.put("message", "Error in writing review!");
			return mp;
		}
		Optional<Customer> res=customerRepo.findById(customerId);
		if(res.isPresent()) {
			review.setCustomer(res.get());
		}
		else {
			mp.put("message", "Error in writing review!");
			return mp;
		}
		review.setBook(book);
		bookRepo.save(book);
		reviewRepo.save(review);
		mp.put("message", "Review written successfully!");
		return mp;
	}
}
