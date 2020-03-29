package com.bookStore.restCrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.restCrud.model.Book;
import com.bookStore.restCrud.service.BookService;

@RestController
public class BookRestController {

   @Autowired
   private BookService bookService;

   /*---Home page---*/
   @RequestMapping(value = "/")
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
   
   /*---Add new book---*/
   @RequestMapping(value = "/book", method = RequestMethod.POST)
   public ResponseEntity<?> save(@RequestBody Book book) {
      long id = bookService.save(book);
      return ResponseEntity.ok().body("New Book has been saved with ID:" + id);
   }

   /*---get a book by id---*/
   @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
   public ResponseEntity<Book> get(@PathVariable("id") long id) {
      Book book = bookService.get(id);
      return ResponseEntity.ok().body(book);
   }

   /*---get all books---*/
   @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
   public ResponseEntity<List<Book>> list() {
      List<Book> books = bookService.list();
      return ResponseEntity.ok().body(books);
   }

   /*---Update a book by id---*/
   @RequestMapping(value = "/updateBook/{id}", method = RequestMethod.PUT)
   public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Book book) {
      bookService.update(id, book);
      return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   /*---Delete a book by id---*/
   @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> delete(@PathVariable("id") long id) {
      bookService.delete(id);
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
}