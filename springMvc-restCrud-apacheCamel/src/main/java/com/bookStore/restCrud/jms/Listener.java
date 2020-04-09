package com.bookStore.restCrud.jms;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import com.bookStore.restCrud.dao.BookDao;
import com.bookStore.restCrud.model.Book;
import com.bookStore.restCrud.service.BookService;

@Component
public class Listener {

	
//	@Autowired
//	BookDao bookdao;
	
	@Autowired
	BookService bookdao;

	@JmsListener(destination = "cm.create", selector = "JMSCorrelationID = 'createc'")
	public void add_newBook(Message objMessage) throws JMSException {
		if (objMessage instanceof ObjectMessage) {
			try {
			//	ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
			  //  mqDelegate.execute(params, amqMessage.getText());
				
				ActiveMQObjectMessage msg = (ActiveMQObjectMessage) objMessage;
				
			//	ObjectMessage objectMessage = (ObjectMessage) objMessage;
				Book book = (Book) msg.getObject();
				if (book != null) {
					System.out.println(book);
					book.setId(null);
					String err = book.getTitle();
					if (err !=null) {
						System.out.println("ok");
						if (bookdao.save(book) != 0) {
		//	Long id = bookdao.save(book);	
							System.out.println("Insert success!");
						}
					} else {
						System.out.println(err);
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			
			 BytesMessage bm = (BytesMessage) objMessage;
			    byte data[] = new byte[(int) bm.getBodyLength()];
			    bm.readBytes(data);
			    
			    Book book = (Book) ((ObjectMessage) bm).getObject();
			    
			//    mqDelegate.execute(params, new String(data));
			
			System.out.println("Not a object message!");
	}
	}
	
	
	/*if (message instanceof ActiveMQTextMessage) {
	    ActiveMQTextMessage amqMessage = (ActiveMQTextMessage) message;
	    mqDelegate.execute(params, amqMessage.getText());
	} else {
	    BytesMessage bm = (BytesMessage) message;
	    byte data[] = new byte[(int) bm.getBodyLength()];
	    bm.readBytes(data);
	    mqDelegate.execute(params, new String(data));
	}
	*/
	
	
	
	
	
	
	
	/*@JmsListener(destination = "cm.update", selector = "JMSCorrelationID = 'updatec'")
	private void updateBook(Message objMessage) {
		if (objMessage instanceof ObjectMessage) {
			try {
				ObjectMessage objectMessage = (ObjectMessage) objMessage;
				Book body = (Book) objectMessage.getObject();
				String err = body.getAuthor();
				if (err !=null) {
					long mac = body.getId();
					if (!StringUtils.isEmpty(mac)) {
					//	mac = normalizeMAC(mac);
						Book sw = bookdao.get(mac);
						if (sw != null) {
							System.out.println("Before update: " + sw.toString());
							if (!StringUtils.isEmpty(body.getName())) {
								sw.setName(body.getName());
							}
							if (!StringUtils.isEmpty(body.getType())) {
								sw.setType(body.getType());
							}
							if (!StringUtils.isEmpty(body.getAddress())) {
								sw.setAddress(body.getAddress());
							}
							if (!StringUtils.isEmpty(body.getVersion())) {
								sw.setVersion(body.getVersion());
							}
							try {
								if (swrepo.save(sw) != null) {
									System.out.println("New: " + sw.toString());
								} else {
									System.out.println("Update failed.");
								}
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}
						} else {
							System.out.println("This switch doesn't exist!");
						}
					}
				} else {
					System.out.println(err);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Not a object message!");
		}
	}
*/
	@JmsListener(destination = "cm.delete", selector = "JMSCorrelationID = 'deletec'")
	private void deleteSwitch(Message objMessage) {
		if (objMessage instanceof ObjectMessage) {
			try {
				ObjectMessage objectMessage = (ObjectMessage) objMessage;
				Book body = (Book) objectMessage.getObject();
				long mac = body.getId();
				if (!StringUtils.isEmpty(mac)) {
				//	mac = normalizeMAC(mac);
					Book sw = bookdao.getBook(mac);
					try {
						if (sw != null) {
							System.out.println("Delete " + sw.toString());
							bookdao.delete(mac);
						} else {
							System.out.println("This switch doesn't exist!");
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Not a object message!");
		}
	}
}
