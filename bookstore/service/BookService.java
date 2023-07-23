package com.rest.bookstore.service;

import com.rest.bookstore.BookRepo;
import com.rest.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
    @Autowired
    BookRepo book_repo;

    // all books
    public List<Book> getAllBooks() {
        List<Book> books = book_repo.findAll();
        System.out.println(books);
        return books;
    }
    // get by id
    public Book getBookById(int id) {
        try {
            Book book = book_repo.findById(id).get();
            return book;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    // get by title
    public Book getBookByCover(String title) {
        title = title.substring(1,title.length()-1).toLowerCase();
        List<Book> books = book_repo.findAll();
        for(Iterator<Book> itr = books.listIterator(); itr.hasNext();){
            Book b = itr.next();
            if(b.getA_title().toLowerCase().equals(title)){
                return book_repo.findById(b.getBookID()).get();
            }
        }
        return null;
    }
    // find all by publish year
    public List<Book> getBooksByPublishYear(int publish_year){
        List<Book> books = book_repo.findAll();
        List<Book> result_books = new ArrayList<>();
        for(Iterator<Book> itr = books.listIterator(); itr.hasNext();) {
            Book b = itr.next();
            if(b.getC_publish_year() == publish_year){
                result_books.add(b);
            }
        }
        if(result_books.size()==0) return null;
        else return result_books;
    }
    // add new book entry
    public Book addNewBook(Book b1) {
        List<Book> books = book_repo.findAll();
        if(!books.stream().anyMatch(b2->b2.getBookID()==b1.getBookID())) {
            return book_repo.save(b1);
        }
        return null;
    }
    // replace a book entry by id
    public Book updateBook(Book b, int bookId) {
        List<Book> books = book_repo.findAll();
        for(int i=0;i<books.size();i++) {
            if(books.get(i).getBookID() == bookId) {
                books.set(i, b);
                book_repo.save(books.get(i));
                return b;
            }
        }
        return null;
    }
    // delete book entry by id
    public Book deleteBookById(int bookId) {
        try{
            Book bdel = book_repo.findById(bookId).get();
            book_repo.delete(bdel);
            return bdel;
        }catch (NoSuchElementException e){
            return null;
        }
    }
    // delete book entry by title
    public Book deleteBookByTitle(String title) {
        title = title.substring(1,title.length()-1).toLowerCase();
        List<Book> books = book_repo.findAll();
        for(Iterator<Book> itr = books.listIterator(); itr.hasNext();){
            Book b = itr.next();
            if(b.getA_title().toLowerCase().equals(title)){
                book_repo.delete(b);
                return b;
            }
        }
        return null;
    }
}
