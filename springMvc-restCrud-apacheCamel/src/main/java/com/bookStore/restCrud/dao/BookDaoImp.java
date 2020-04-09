package com.bookStore.restCrud.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bookStore.restCrud.model.Book;

@Repository("BookDao")
public class BookDaoImp implements BookDao {

   @Autowired
   private SessionFactory sessionFactory;


   @Transactional(rollbackFor = { Exception.class })
   public long save(Book book) throws Exception {
	   
	   Session session = this.sessionFactory.getCurrentSession();
	   try {
			if (book != null) {
				session.saveOrUpdate(book);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	   return book.getId();
   }
 

   @Transactional(rollbackFor = { Exception.class })
   public Book get(long id) throws Exception{
	   Session session = this.sessionFactory.getCurrentSession();
	   Book book = new Book();
	   try {
		    if(id != 0) {
		     book =  session.get(Book.class, id);
		      }
	       }catch(Exception exception) {
			exception.printStackTrace();
	          }
	    return book;
   }


   @Transactional(rollbackFor = { Exception.class })
   public List<Book> list() throws Exception{
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Book> cq = cb.createQuery(Book.class);
      Root<Book> root = cq.from(Book.class);
      cq.select(root);
      Query<Book> query = session.createQuery(cq);
      return query.getResultList();
   }

   @Transactional(rollbackFor = { Exception.class })
   public void update(long id, Book book) throws Exception{
      Session session = sessionFactory.getCurrentSession();
      Book book2 = session.byId(Book.class).load(id);
      book2.setTitle(book.getTitle());
      book2.setAuthor(book.getAuthor());
      session.flush();
   }


   @Transactional(rollbackFor = { Exception.class })
   public void delete(long id) throws Exception{
	   Session session = this.sessionFactory.getCurrentSession();
      try {
	    	  if(id != 0) {
	    	  Book book = session.byId(Book.class).load(id);
	          session.delete(book);
	    	      }
          }catch(Exception exception) {
  		exception.printStackTrace();
      }
   }
} 
