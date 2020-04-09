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

   
   public long save(Book book) throws Exception{
      return bookDao.save(book);
   }


   public Book getBook(long id) throws Exception{
      return bookDao.get(id);
   }


   public List<Book> listAllBooks() throws Exception{
      return bookDao.list();
   }

   public void update(long id, Book book) throws Exception{
      bookDao.update(id, book);
   }

 
   public void delete(long id) throws Exception{
      bookDao.delete(id);
   }

}
