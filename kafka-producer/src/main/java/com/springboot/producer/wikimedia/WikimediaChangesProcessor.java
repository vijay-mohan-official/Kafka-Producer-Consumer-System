package com.springboot.producer.wikimedia;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikimediaChangesProcessor {

	private static final Logger log = LoggerFactory.getLogger(WikimediaChangesProcessor.class);
	
	private KafkaTemplate<String, String> kafkaTemplate;

	public WikimediaChangesProcessor(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendMessage() throws InterruptedException {
		
		String topic = "wikimediarecentchange";
		
		//To read real time stream data from Wikimedia , we are using event source
		EventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		
		EventSource.Builder builder = new EventSource.Builder(eventHandler,URI.create(url));
		EventSource eventSource = builder.build();
		eventSource.start();
		
		TimeUnit.MINUTES.sleep(10);
		
	}
	
}
