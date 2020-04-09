package com.bookStore.restCrud.dao;

import java.util.List;

import com.bookStore.restCrud.model.Book;

public interface BookDao {

   long save(Book book) throws Exception;

   Book get(long id) throws Exception;

   List<Book> list() throws Exception;

   void update(long id, Book book) throws Exception;

   void delete(long id) throws Exception;

}
