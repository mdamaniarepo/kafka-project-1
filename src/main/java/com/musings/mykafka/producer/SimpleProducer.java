package com.musings.mykafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("simpleProducer")
public class SimpleProducer {

	@Autowired
	@Qualifier("producerProperties")
	private Properties configProperties;

	public void sendMessages() {
		Producer<String, String> producer = new KafkaProducer<>(this.configProperties);
		for (int i = 1; i <= 10; i++) {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("myTopic", String.valueOf(i), "Message " + i);
			producer.send(record);
		}
		producer.close();
		System.out.println("Message sent");
	}

	public Properties getConfigProperties() {
		return configProperties;
	}

	public void setConfigProperties(Properties configProperties) {
		this.configProperties = configProperties;
	}

}
