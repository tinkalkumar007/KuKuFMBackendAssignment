package com.kuku.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuku.entity.Book;
import com.kuku.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(String writer,String genre,float ratings){
		//System.out.println(writer+", "+genre+", "+ratings);
		if(writer.equals("null") && genre.equals("null") && ratings==0) {
			
			return bookRepository.findAll();
		}
		else if(genre.equals("null")) {
			if(writer.equals("null")) {
				return bookRepository.findAllBooksByRating(ratings);
			}
			else if(ratings==0) {
				return bookRepository.findAllBooksByWriter(writer);
			}
			else {
				return bookRepository.findBooksByRatingAndAuthor(writer, ratings);
			}
		}
		else if(writer.equals("null")) {
			if(ratings==0) {
				return bookRepository.findAllBooksByGenre(genre);
			}
			else {
				return bookRepository.findBooksByRatingAndGener(ratings, genre);
			}
		}
		else if(ratings==0) {
			return bookRepository.findBooksByGenerAndAuthor(writer, genre);
		}
		return bookRepository.findAllBooksByRatingAuthorGenre(writer, ratings, genre);
	}
	public Book findBookById(Integer id) {
		Optional<Book> result= bookRepository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		return null;
	}
}

