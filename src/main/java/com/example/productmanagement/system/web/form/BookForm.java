package com.example.productmanagement.system.web.form;
import com.example.productmanagement.system.persistence.entity.Book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <h2>BookForm Class</h2>
 * <p>
 * Process for Displaying BookForm
 * </p>
 * 
 * @author Myat Noe Khin
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
	/**
     * <h2>bookId</h2>
     * <p>
     * bookId
     * </p>
     */
    private Long bookId;
    
    /**
	 *  <h2>title</h2>
	 *  <p>
	 *  title
	 *  </p>
	 */
    private String title;
	
	/**
	 *  <h2>author</h2>
	 *  <p>
	 *  author
	 *  </p>
	 */
	private String author;
	
	/**
	 *  <h2>genre</h2>
	 *  <p>
	 *  genre
	 *  </p>
	 */
	private String genre;
	
	/**
	 *  <h2>price</h2>
	 *  <p>
	 *  price
	 *  </p>
	 */
	private double price;
	
	/**
     * <h2>Constructor of BookDTO</h2>
     * <p>
     * Constructor of BookDTO
     * </p>
     */
	public BookForm(Book book) {
		this.bookId = book.getBookId();
		this.title = book.getTitle();
		this.author = book.getAuthor();
		this.genre = book.getGenre();
		this.price = book.getPrice();
	}
}
