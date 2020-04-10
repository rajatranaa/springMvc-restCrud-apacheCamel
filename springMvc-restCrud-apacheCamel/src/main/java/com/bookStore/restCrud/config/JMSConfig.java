package com.bookStore.restCrud.config;



import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@EnableJms
@Configuration
@ComponentScan("com.bookStore.restCrud.config")
public class JMSConfig {	
	
	private static final String URL = "vm://localhost?broker.persistent=false";
//	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
//	private static final String URL = "http://localhost:8080";
	
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";
	
	
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(URL);
		connectionFactory.setUserName(USERNAME);
		connectionFactory.setPassword(PASSWORD);
	//	connectionFactory.setTrustedPackages(Arrays.asList("org.api","java.util","com.bookStore.restCrud.model.Book"));
		connectionFactory.setTrustAllPackages(true);
	//	connectionFactory.setTrustedPackages(Arrays.asList("com.bookStore.restCrud.config"));
		
		return connectionFactory;
	}
	
	
	@Bean
	public ActiveMQComponent activeMQComponent() {
		ActiveMQComponent activeMQComponent = new ActiveMQComponent();
		activeMQComponent.setConnectionFactory(connectionFactory());
		activeMQComponent.setDeliveryPersistent(true);
		activeMQComponent.setExplicitQosEnabled(true);
		activeMQComponent.setTimeToLive(3*60*60*1000);
		return activeMQComponent;
	}
	
	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setConcurrency("1-1");
		
		factory.setPubSubDomain(false);

		return factory;
	}
}
