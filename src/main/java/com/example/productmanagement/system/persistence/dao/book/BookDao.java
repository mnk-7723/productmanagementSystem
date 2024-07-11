package com.example.productmanagement.system.persistence.dao.book;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.productmanagement.system.persistence.entity.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Long>{
	
	void deleteById(Long bookId);
	
	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
	 Page<Book> findBookByTitle(@Param("title") String title, Pageable pageable);

	 @Query("SELECT b FROM Book b WHERE b.genre LIKE %:genre%")
	Page<Book> findByGenre(@Param("genre") String genre, Pageable pageable);

	@Query("SELECT b FROM Book b WHERE b.title LIKE %:title% AND (b.genre LIKE %:genre% OR b.genre IS NULL)")
	Page<Book> findByTitleContainingAndGenre(@Param("title") String title, @Param("genre") String genre, Pageable pageable);

	 @Query("SELECT DISTINCT b.genre FROM Book b")
	 List<String> findDistinctGenres();	
	 

}
