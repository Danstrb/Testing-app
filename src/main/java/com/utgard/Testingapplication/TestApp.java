package com.utgard.Testingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApp {

	public static void main(String[] args) {
		System.out.println("test");
		SpringApplication.run(TestApp.class, args);
	}
}
// TODO: find out how exactly postman works for posting so that we can add some students to the db
// TODO: also there was o way to install a small mock database - look where it was video-wise + make notes
// NOTE: Database seems created - with sequence, table, though log does not seem to reflect that -> lets make postman work
