package com.example.productmanagement.system.persistence.entity;

import com.example.productmanagement.system.web.form.BookForm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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


	@Column(name ="image_url")
	private String imageUrl;

	@NotBlank(message = "The title is required.")
	@Column(name = "title")
	private String title;

	@NotEmpty(message = "The author name is required.")
	@Column(name = "author")
	private String author;

	@Column(name = "genre")
	private String genre;

	@Column(name = "price")
	private double price;

	public Book(BookForm bookForm) {
		this.bookId = bookForm.getBookId();
		this.title = bookForm.getTitle();
		this.author = bookForm.getAuthor();
		this.genre = bookForm.getGenre();
		this.price = bookForm.getPrice();
		this.imageUrl = bookForm.getImageUrl();
	}

}
