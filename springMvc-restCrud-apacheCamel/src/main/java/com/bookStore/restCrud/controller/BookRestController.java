package com.bookStore.restCrud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BookRestController {

   @Autowired
   private BookService bookService;
   
	@Autowired
	CamelContext camelContext;

   //Home page
   @RequestMapping(value = "/")
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		return model;
	}
   
   //Add new book
   @RequestMapping(value = "/book", method = RequestMethod.POST)
   public ResponseEntity<HttpStatus> save(@RequestBody Book book, HttpServletRequest req) throws Exception{
	   ProducerTemplate pt = camelContext.createProducerTemplate();
	   System.out.println("on the way.....");
		String destination = "direct:cm.create";
		System.out.println("Send message to " + destination);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(book);
		System.out.println(jsonString);
		
	//	pt.sendBody(destination, jsonString);
		pt.sendBody(destination, book);
		return ResponseEntity.ok(HttpStatus.OK);
		
   //   long id = bookService.save(book);
   //   return ResponseEntity.ok().body("New Book saved with ID:" + id);
   }
 
   
  //get a book by id
   @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
   public ResponseEntity<Book> get(@PathVariable("id") long id) throws Exception{
      Book book = bookService.getBookById(id);
      return ResponseEntity.ok().body(book);
   }

   //get all books
   @RequestMapping(value = "/getAllBooks", method = RequestMethod.GET)
   public ResponseEntity<List<Book>> list() throws Exception{
      List<Book> books = bookService.getAllBooks();
      return ResponseEntity.ok().body(books);
   }

  //Update a book
   @RequestMapping(value = "/updateBook", method = RequestMethod.PUT)
   public ResponseEntity<?> update(@RequestBody Book book) throws Exception {
	   ProducerTemplate pt = camelContext.createProducerTemplate();
	   String destination = "direct:cm.update";
		System.out.println("Send message to " + destination);
		pt.sendBody(destination, book);
     
		return ResponseEntity.ok().body("Book has been updated successfully.");
   }

   //Delete a book by id
   @RequestMapping(value = "/deleteBook/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<?> delete(@PathVariable("id") long id) throws Exception{
	   ProducerTemplate pt = camelContext.createProducerTemplate();
	   String destination = "direct:cm.delete";
		System.out.println("Send message to " + destination);
		pt.sendBody(destination, id);
		
      return ResponseEntity.ok().body("Book has been deleted successfully.");
   }
}


