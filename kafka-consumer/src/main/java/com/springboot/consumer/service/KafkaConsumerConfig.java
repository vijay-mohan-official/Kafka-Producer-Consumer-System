package com.springboot.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.springboot.consumer.entity.WikimediaEntity;
import com.springboot.consumer.repository.WikimediaRepository;

@Service
public class KafkaConsumerConfig {
	
	private static final Logger log = LoggerFactory.getLogger(KafkaConsumerConfig.class);
	
	private WikimediaRepository repo;
	
	public KafkaConsumerConfig(WikimediaRepository repo) {
		this.repo = repo;
	}

	@KafkaListener(topics = "wikimediarecentchange",groupId = "myGroup")
	public void consumer(String eventMessage) {
		log.info(String.format("Message received : %s", eventMessage));
		WikimediaEntity data = new WikimediaEntity();
		data.setEventData(eventMessage);
		
		repo.save(data);	
	}
}
