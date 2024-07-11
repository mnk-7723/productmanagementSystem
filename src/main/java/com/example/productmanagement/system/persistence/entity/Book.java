package com.example.productmanagement.system.persistence.entity;

import com.example.productmanagement.system.web.form.BookForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * <h2>Book Class</h2>
 * <p>
 * Process for Displaying Book
 * </p>
 * 
 * @author Myat Noe Khin
 *
 */
@Setter
@Getter 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")

public class Book {
	/**
     * <h2>bookId</h2>
     * <p>
     * bookId
     * </p>
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
	
	/**
	 *  <h2>title</h2>
	 *  <p>
	 *  title
	 *  </p>
	 */
	@Column(name = "title")
	private String title;
	
	/**
	 *  <h2>author</h2>
	 *  <p>
	 *  author
	 *  </p>
	 */
	@Column(name = "author")
	private String author;
	
	/**
	 *  <h2>genre</h2>
	 *  <p>
	 *  genre
	 *  </p>
	 */
	@Column(name = "genre")
	private String genre;
	
	/**
	 *  <h2>price</h2>
	 *  <p>
	 *  price
	 *  </p>
	 */
	@Column(name = "price")
	private double price;
	
	/**
     * <h2>Constructor for Book</h2>
     * <p>
     * Constructor for Book
     * </p>
     * 
     * @param bookForm
     */
	public Book(BookForm bookForm) {
		this.bookId = bookForm.getBookId();
		this.title = bookForm.getTitle();
		this.author = bookForm.getAuthor();
		this.genre = bookForm.getGenre();
		this.price = bookForm.getPrice();
	}
	
}
