package com.example.productmanagement.system.bl.service.book.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.productmanagement.system.bl.service.book.BookService;
import com.example.productmanagement.system.persistence.dao.book.BookDao;
import com.example.productmanagement.system.persistence.entity.Book;
import com.example.productmanagement.system.web.form.BookForm;

@Service
@Transactional
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao bookDao;

	@Override
	public void addBook(BookForm bookForm) {
		Book book = new Book(bookForm);
		bookDao.save(book);
		
	}

	@Override
	public Page<Book> getBooks(Pageable pageable) {
	    return bookDao.findAll(pageable);
	}

	@Override
	public Book getBookById(Long id) {
		return bookDao.findById(id).orElse(null);
	}

	@Override
	public void deleteBookById(Long bookId) {
		bookDao.deleteById(bookId);
		
	}

	@Override
	public void updateBook(BookForm bookForm) {
		bookDao.save(new Book(bookForm));
		
	}

	@Override
	public Page<Book> searchBook(String title, String genre, int page, int size) {
		 Pageable pageable = PageRequest.of(page, size);
		if( title != null && !title.isEmpty() && genre != null && !genre.isEmpty()) {
			return bookDao.findByTitleContainingAndGenre(title, genre, pageable);
		}else if( title != null && !title.isEmpty()) {
			return bookDao.findBookByTitle(title, pageable);
		}else if( genre != null && !genre.isEmpty()) {
			return bookDao.findByGenre(genre, pageable);
		}else {
			return bookDao.findAll(pageable);
		}
	}

	@Override
	public List<String> findAllGenres() {
		  return bookDao.findDistinctGenres();
	}

	@Override
	public Page<Book> findByGenre(String genre, Pageable pageable) {
		return bookDao.findByGenre(genre, pageable);
	}



}
