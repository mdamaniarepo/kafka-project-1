package com.musings.mykafka.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("simpleConsumer")
public class SimpleConsumer {

	@Autowired
	@Qualifier("consumerProperties")
	private Properties consumerProperties;

	@Value("myTopic")
	private String topicNames;

	@SuppressWarnings("resource")
	public void receiveMessages() {
		Consumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
		consumer.subscribe(Collections.singletonList(topicNames));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(1000);

			for (ConsumerRecord<String, String> record : records) {
				System.out.println("Topic :" + record.topic() + 
						", Partition :" + record.partition() +
						", Offset :" + record.offset() +
						", Value :" + record.value());
			}
			
			consumer.commitSync();
		}
	}

	public Properties getConsumerProperties() {
		return consumerProperties;
	}

	public void setConsumerProperties(Properties consumerProperties) {
		this.consumerProperties = consumerProperties;
	}

	public String getTopicNames() {
		return topicNames;
	}

	public void setTopicNames(String topicNames) {
		this.topicNames = topicNames;
	}
	
	

}
