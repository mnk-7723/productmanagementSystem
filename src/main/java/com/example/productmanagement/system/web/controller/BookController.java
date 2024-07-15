package com.example.productmanagement.system.web.controller;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.productmanagement.system.bl.service.book.BookService;
import com.example.productmanagement.system.persistence.entity.Book;
import com.example.productmanagement.system.web.form.BookForm;

/**
 * <h2>BookController Class</h2>
 * <p>
 * Process for Displaying BookController
 * </p>
 * 
 * @author Myat Noe Khin
 *
 */
@Controller
@RequestMapping(value = "/book")
public class BookController {
	/**
	 * <h2>bookService</h2>
	 * <p>
	 * bookService
	 * </p>
	 */
	@Autowired
	private BookService bookService;

	// READ
	@GetMapping("/list")
		public String listProducts(@RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "size", defaultValue = "5") int size, Model model) {
		 Page<Book> bookPage = bookService.getBooks(PageRequest.of(page, size));
		  model.addAttribute("books", bookPage.getContent());
		    model.addAttribute("genres", bookService.findAllGenres());
		    model.addAttribute("title", ""); // Empty title for non-search scenario
		    model.addAttribute("genre", ""); // Empty genre for non-search scenario
		    model.addAttribute("currentPage", page);
		    model.addAttribute("totalPages", bookPage.getTotalPages());
		    model.addAttribute("size", size);
		    return "book/list";
	}

	// CREATE
	@GetMapping("/add")
	public ModelAndView showAddBookForm() {
		ModelAndView modelAndView = new ModelAndView("book/register");
		modelAndView.addObject("bookForm", new BookForm());
		modelAndView.addObject("pageTitle", "Add Book | BookStore");
		return modelAndView;
	}

	// CREATE
	@PostMapping("/register")
	public String addBook(@ModelAttribute("bookForm") BookForm bookForm) {
		bookService.addBook(bookForm);
		return "redirect:/book/list";
	}

	// UPDATE
	@GetMapping("/edit/{id}")
	public String showEditBookForm(@PathVariable("id") Long id,
								   @RequestParam(value = "title", required = false) String title,
								   @RequestParam(value = "genre", required = false) String genre,
								   @RequestParam(value = "page", defaultValue = "0") int page,
								   @RequestParam(value = "size", defaultValue = "5") int size,
								   Model model) {
		Book book = bookService.getBookById(id);
		model.addAttribute("bookForm", book);
		model.addAttribute("pageTitle", "Edit Book | BookStore");
		model.addAttribute("title", title);
		model.addAttribute("genre", genre);
		model.addAttribute("currentPage", page);
		model.addAttribute("pageSize", size);
		return "book/edit";
	}

	// UPDATE
	@PostMapping("/update")
	public String updateBook(@ModelAttribute("bookForm") BookForm bookForm,
							 @RequestParam(value = "searchTitle") String searchTitle,
							 @RequestParam(value = "searchGenre") String searchGenre,
							 @RequestParam(value = "currentPage") int currentPage,
							 @RequestParam(value = "pageSize") int pageSize) {
		bookService.updateBook(bookForm);
		String redirectUrl ="redirect:/book/search?title=" + URLEncoder.encode(searchTitle, StandardCharsets.UTF_8) +
							"&genre="+ URLEncoder.encode(searchGenre, StandardCharsets.UTF_8) +
							"&page=" + currentPage +
							"&size=" + pageSize;

		return redirectUrl;
	}

	// DELETE
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBookById(id);
		return "redirect:/book/list";
	}

	// SEARCH
	@GetMapping("/search")
	public String searchBooks(@RequestParam(value = "title", required = false) String title,
							  @RequestParam(value = "genre", required = false) String genre,
							  @RequestParam(value = "page", defaultValue = "0") int page,
							  @RequestParam(value = "size", defaultValue = "5") int size,
							  Model model) {
		  Page<Book> bookPage = bookService.searchBook(title, genre, page, size);
		
		model.addAttribute("books", bookPage.getContent());
        model.addAttribute("genres", bookService.findAllGenres());
        model.addAttribute("title", title); // Add title to the model
        model.addAttribute("genre", genre); // Add genre to the model
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookPage.getTotalPages());
        model.addAttribute("size", size);
        
        return "book/list";
	}

}
