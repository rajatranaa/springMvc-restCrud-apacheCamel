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

   
   public long saveBook(Book book) throws Exception{
      return bookDao.saveBook(book);
   }


   public Book getBookById(long id) throws Exception{
      return bookDao.getBookById(id);
   }


   public List<Book> getAllBooks() throws Exception{
      return bookDao.getAllBooks();
   }

   public void updateBookById(long id, Book book) throws Exception{
      bookDao.updateBookById(id, book);
   }

 
   public void deleteBookById(long id) throws Exception{
      bookDao.deleteBookById(id);
   }

}
