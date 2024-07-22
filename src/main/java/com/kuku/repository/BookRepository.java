package com.kuku.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kuku.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query("SELECT b from Book b WHERE b.writer=:writer AND b.rating>=:rating")
	List<Book> findBooksByRatingAndAuthor(@Param("writer") String writer,@Param("rating") float rating);
	
	@Query("SELECT b from Book b WHERE b.writer=:writer")
	List<Book> findAllBooksByWriter(@Param("writer") String writer);
	
	@Query("SELECT b from Book b WHERE b.rating>=:rating")
	List<Book> findAllBooksByRating(@Param("rating") float rating);
	
	@Query("SELECT b from Book b WHERE b.writer=:writer AND b.rating>=:rating AND b.genre=:genre")
	List<Book> findAllBooksByRatingAuthorGenre(@Param("writer") String writer,@Param("rating") float rating,@Param("genre") String genre);
	
	@Query("SELECT b from Book b WHERE b.genre=:genre")
	List<Book> findAllBooksByGenre(@Param("genre") String genre);
	
	@Query("SELECT b from Book b WHERE b.writer=:writer AND b.genre=:genre")
	List<Book> findBooksByGenerAndAuthor(@Param("writer") String writer,@Param("genre") String genre);
	
	@Query("SELECT b from Book b WHERE b.rating>=:rating AND b.genre=:genre")
	List<Book> findBooksByRatingAndGener(@Param("rating") float rating,@Param("genre") String genre);
}
