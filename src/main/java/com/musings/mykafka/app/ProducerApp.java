package com.musings.mykafka.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musings.mykafka.producer.SimpleProducer;

public class ProducerApp {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml")) {
			SimpleProducer producer = context.getBean("simpleProducer", SimpleProducer.class);
			producer.sendMessages();
		}

	}

}
