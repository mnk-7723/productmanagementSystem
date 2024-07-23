package com.example.productmanagement;

import com.example.productmanagement.system.bl.service.book.BookService;
import com.example.productmanagement.system.persistence.entity.Book;
import com.example.productmanagement.system.web.form.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private BookService bookService;

    @GetMapping("/bookList")
   public List<Book> bookList(@RequestParam(value = "page" , defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "1") int size){
        Page<Book> bookPage = bookService.getBooks(PageRequest.of(page, size));
        return bookPage.getContent();
    }

    @PostMapping("/add")
    public Book newBook(@RequestBody BookForm bookForm){
        return bookService.addBook(bookForm);
    }

    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") Long id, @RequestBody BookForm bookForm){
      bookForm.setBookId(id);
     bookService.updateBook(bookForm);
     return "Book Updated Successfully!";
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody BookForm bookForm){
        return bookService.updateBook(bookForm);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBookById(id);

        return "Book deleted successfully!";
    }


}
