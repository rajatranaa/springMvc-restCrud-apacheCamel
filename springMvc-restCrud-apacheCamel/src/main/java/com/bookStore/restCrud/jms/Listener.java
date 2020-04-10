package com.bookStore.restCrud.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import com.bookStore.restCrud.model.Book;
import com.bookStore.restCrud.service.BookService;

@Component
public class Listener {

	
//	@Autowired
//	BookDao bookdao;
	
	@Autowired
	BookService bookService;

	@JmsListener(destination = "cm.create", selector = "JMSCorrelationID = 'createc'")
	public void add_newBook(Message objMessage) throws JMSException {
		if (objMessage instanceof ObjectMessage) {
			try {
			//	ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
			  //  mqDelegate.execute(params, amqMessage.getText());
				ActiveMQObjectMessage msg = (ActiveMQObjectMessage) objMessage;
				
				Book book = (Book) msg.getObject();
				if (book != null) {
					System.out.println("ok");
					if (bookService.saveBook(book) != 0) {
					//	bookdao.save(book);	
						System.out.println("Insert success!");
					 }
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Not a object message!");
	}
	}
	
	
	
	@JmsListener(destination = "cm.update", selector = "JMSCorrelationID = 'updatec'")
	public void updateBook(Message objMessage) {
		if (objMessage instanceof ObjectMessage) {
			try {
			
				ActiveMQObjectMessage msg = (ActiveMQObjectMessage) objMessage;
				
				Book book = (Book) msg.getObject();
				if (book != null) {
					System.out.println("ok");
					bookService.updateBookById(book.getId(), book);
				}
				else {
					System.out.println("Book not found");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Not a object message!");
	}
	}

	@JmsListener(destination = "cm.delete", selector = "JMSCorrelationID = 'deletec'")
	public void deleteBook(Message objMessage) {
		if (objMessage instanceof ObjectMessage) {
			try {
				ObjectMessage objectMessage = (ObjectMessage) objMessage;
				long id =  (long) objectMessage.getObject();
						if (id != 0) {
							bookService.deleteBookById(id);
						} else {
							System.out.println("This id/book doesn't exist!");
						}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Not a object message!");
		}
	}
}
