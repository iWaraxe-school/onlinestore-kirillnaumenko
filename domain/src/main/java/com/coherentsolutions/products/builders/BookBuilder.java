package com.coherentsolutions.products.builders;

import com.coherentsolutions.products.Book;

public class BookBuilder {
    private Book book;

    public BookBuilder(){
        this.book = new Book();
    }

    public BookBuilder setAuthor(String author){
        this.book.setAuthor(author);

        return this;
    }

    public BookBuilder setName(String name){
        this.book.setName(name);

        return this;
    }

    public BookBuilder setPrice(Double price){
        this.book.setPrice(price);

        return this;
    }

    public BookBuilder setRate(Double rate){
        this.book.setRate(rate);

        return this;
    }

    public Book build(){
        return this.book;
    }
}
