package com.bookStore.restCrud.dao;

import java.util.List;

import com.bookStore.restCrud.model.Book;

public interface BookDao {

   long save(Book book);

   Book get(long id);

   List<Book> list();

   void update(long id, Book book);

   void delete(long id);

}
