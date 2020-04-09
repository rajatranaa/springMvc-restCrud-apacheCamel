package com.bookStore.restCrud.service;

import java.util.List;

import com.bookStore.restCrud.model.Book;

public interface BookService {

   long save(Book book) throws Exception;
   Book getBook(long id) throws Exception;
   List<Book> listAllBooks() throws Exception;
   void update(long id, Book book) throws Exception;
   void delete(long id) throws Exception;
}
