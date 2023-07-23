package com.rest.bookstore.controller;

import com.rest.bookstore.model.Book;
import com.rest.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @RequestMapping("/store")
    public String welcome(){
        return "Welcome to the Book Store";
    }

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> viewBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/books/{id}")
    public Book viewBookById(@PathVariable int id){
        return bookService.getBookById(id);
    }
    @GetMapping("/books/title={title}")
    public Book viewBookByCover(@PathVariable String title){
        return bookService.getBookByCover(title);
    }
    @GetMapping("/books/year={publish_year}")
    public List<Book> viewBooksByPublishYear(@PathVariable int publish_year){
        return bookService.getBooksByPublishYear(publish_year);
    }
    @PostMapping("/books")
    public Book addNewBook(@RequestBody Book b){
        return bookService.addNewBook(b);
    }
    @PutMapping("/books/{bookId}")
    public Book replaceBook(@RequestBody Book b, @PathVariable int bookId){
        return bookService.updateBook(b, bookId);
    }
    @DeleteMapping("/books/{bookId}")
    public Book deleteBook(@PathVariable int bookId){
        return bookService.deleteBookById(bookId);
    }
    @DeleteMapping("/books/title={title}")
    public Book deleteBook(@PathVariable String title){
        return bookService.deleteBookByTitle(title);
    }
}
