package com.musings.mykafka.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.mykafka.consumer.SimpleConsumer;

public class ConsumerApp {
	
	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {
			SimpleConsumer consumer = context.getBean("simpleConsumer", SimpleConsumer.class);
			consumer.receiveMessages();;
		}

	}

}
