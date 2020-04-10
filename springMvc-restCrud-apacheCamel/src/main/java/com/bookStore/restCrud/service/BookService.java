package com.bookStore.restCrud.service;

import java.util.List;

import com.bookStore.restCrud.model.Book;

public interface BookService {

   long saveBook(Book book) throws Exception;
   
   Book getBookById(long id) throws Exception;
   
   List<Book> getAllBooks() throws Exception;
   
   void updateBookById(long id, Book book) throws Exception;
   
   void deleteBookById(long id) throws Exception;
}
