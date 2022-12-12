package com.example1.ris;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RisApplication {

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(RisApplication.class, args);
	}

//		@EventListener(ApplicationReadyEvent.class)
//		public void sendEmail(){
//			String welcome = "Welcome, Miha";
//			String body = "Thank you for registering on MojInstruktor app!";
//			emailSenderService.sendEmail("prah.miha1@gmail.com", welcome, body);
//		}
}
