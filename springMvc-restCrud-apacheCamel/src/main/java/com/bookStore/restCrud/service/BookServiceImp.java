package com.bookStore.restCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.restCrud.dao.BookDao;
import com.bookStore.restCrud.model.Book;

@Service("BookService")
@Transactional
public class BookServiceImp implements BookService {

	
   @Autowired
   BookDao bookDao;

   
   public long save(Book book) {
      return bookDao.save(book);
   }


   public Book get(long id) {
      return bookDao.get(id);
   }


   public List<Book> list() {
      return bookDao.list();
   }

   public void update(long id, Book book) {
      bookDao.update(id, book);
   }

 
   public void delete(long id) {
      bookDao.delete(id);
   }

}
