package com.kuku.entity;

import java.util.Base64;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bookId")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private String title;
	private String writer;
	private String description;
	private String isbn;
	private float rating;
	private String genre;
	private byte[] image;
	private float price;
	private Date published;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
	@JsonManagedReference
	private Set<Review> reviews = new HashSet<Review>();
	
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public float getRating() {
		float avgRating=0.0f;
		float sum=0.0f;
		for(Review review:reviews) {
			sum+=review.getRating();
		}
		if(reviews.size()==0) {
			rating=sum;
		}
		else {
			avgRating=sum/reviews.size();
			rating=avgRating;
		}
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
	public Set<Review> getReviews() {
		TreeSet<Review> sortedReviews=new TreeSet<>(new Comparator<Review> () {
			@Override
			public int compare(Review review1, Review review2) {
				return review2.getReviewTime().compareTo(review1.getReviewTime());
			}
			
		});
		sortedReviews.addAll(reviews);
		return sortedReviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
}