package com.kuku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kuku.entity.Book;
import com.kuku.service.BookService;

@RestController
@RequestMapping("/api/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/findAll")
	public List<Book> findAllBooks(@RequestParam(required=false) String writer,@RequestParam(required=false) String genre,@RequestParam(required=false) Float ratings){
//		if(writer.equals("null")||writer.equals("")) {
//			writer=null;
//		}
//		if(genre.equals("null")||genre.equals("")) {
//			genre=null;
//		}
		//System.out.println(ratings);
		return bookService.findAllBooks(writer, genre, ratings);
	}
	
	@GetMapping("/find")
	public Book findBook(@RequestParam("id") Integer id) {
		return bookService.findBookById(id);
	}
	
}
