package com.example.productmanagement;

import com.example.productmanagement.system.bl.service.book.BookService;
import com.example.productmanagement.system.persistence.entity.Book;
import com.example.productmanagement.system.web.form.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    private BookService bookService;

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping("/bookList")
   public List<Book> bookList(@RequestParam(value = "page" , defaultValue = "0") int page,
                              @RequestParam(value = "size", defaultValue = "1") int size){
        Page<Book> bookPage = bookService.getBooks(PageRequest.of(page, size));
        return bookPage.getContent();
    }

    @PostMapping("/add")
    public Book newBook(@RequestPart("image") MultipartFile image,
                        @RequestParam("title") String title,
                        @RequestParam("author") String author,
                        @RequestParam("genre") String genre,
                        @RequestParam("price") Double price) throws IOException{
        BookForm bookForm = new BookForm();
        bookForm.setTitle(title);
        bookForm.setAuthor(author);
        bookForm.setGenre(genre);
        bookForm.setPrice(price);
        bookForm.setImage(image);

        String imageUrl = saveImage(bookForm.getImage());
        bookForm.setImageUrl(imageUrl);
        return bookService.addBook(bookForm);
    }

    @PutMapping("/updateBook/{id}")
    public String updateBook(@PathVariable("id") Long id, @RequestBody BookForm bookForm){
      bookForm.setBookId(id);
     bookService.updateBook(bookForm);
     return "Book Updated Successfully!";
    }

    @PostMapping("/update")
    public Book updateBook(@RequestParam("id") Long id,
                           @RequestPart("image") MultipartFile image,
                           @RequestParam("title") String title,
                           @RequestParam("author") String author,
                           @RequestParam("genre") String genre,
                           @RequestParam("price") Double price) throws IOException{

        BookForm bookForm = new BookForm();
        bookForm.setBookId(id);
        bookForm.setTitle(title);
        bookForm.setAuthor(author);
        bookForm.setGenre(genre);
        bookForm.setPrice(price);
        bookForm.setImage(image);
        String imageUrl = saveImage(bookForm.getImage());
        bookForm.setImageUrl(imageUrl);

        return bookService.updateBook(bookForm);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id){
        bookService.deleteBookById(id);

        return "Book deleted successfully!";
    }
    private String saveImage(MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            try {
                Resource resource = resourceLoader.getResource("classpath:/static/uploads/book-images/");
                Path uploadPath = Paths.get(resource.getURI());

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                return fileName;
            }catch (IOException e) {
                throw new IOException("Could not save image file", e);
            }
        }
        return null;
    }

}
