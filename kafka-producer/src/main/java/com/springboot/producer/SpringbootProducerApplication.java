package com.springboot.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.producer.wikimedia.WikimediaChangesProcessor;

@SpringBootApplication
public class SpringbootProducerApplication implements CommandLineRunner{
	
	public static void main(String args[]){
		SpringApplication.run(SpringbootProducerApplication.class);
	}
	
	@Autowired
	private WikimediaChangesProcessor wikimediaChangesProcessor;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		wikimediaChangesProcessor.sendMessage();
	}
	
}
