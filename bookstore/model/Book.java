package com.rest.bookstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Book {
    @Id
    private int bookID = -1;
    private String a_title;
    private String b_author;
    private int c_publish_year;
    private float d_price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookID == book.bookID && c_publish_year == book.c_publish_year && Double.compare(book.d_price, d_price) == 0 && Objects.equals(a_title, book.a_title) && Objects.equals(b_author, book.b_author);
    }
    @Override
    public int hashCode() {
        return Objects.hash(bookID, a_title, b_author, c_publish_year, d_price);
    }

    @Override
    public String toString() {
        return "Book{" +"bookID=" + bookID +", a_title='" + a_title + '\'' +", b_author='" + b_author + '\'' +", c_publish_year=" + c_publish_year +", d_price=" + d_price +"}\n";
    }

    // constructors
    public Book(){}
    public Book(int bookID, String title, String author, int publish_year, float price){
        super();
        this.bookID = bookID;
        this.a_title = title;
        this.b_author = author;
        this.c_publish_year = publish_year;
        this.d_price = price;
    }


    // getters and setters
    public int getBookID() { return bookID; }
    public void setBookID(int bookID) { this.bookID = bookID; }
    public String getA_title() { return a_title; }
    public void setA_title(String a_title) { this.a_title = a_title; }
    public String getB_author() { return b_author; }
    public void setB_author(String b_author) { this.b_author = b_author; }
    public int getC_publish_year() { return c_publish_year; }
    public void setC_publish_year(int c_publish_year) { this.c_publish_year = c_publish_year; }
    public float getD_price() { return d_price; }
    public void setD_price(float d_price) { this.d_price = d_price; }

}
