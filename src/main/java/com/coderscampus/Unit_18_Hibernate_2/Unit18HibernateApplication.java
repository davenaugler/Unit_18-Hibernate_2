package com.coderscampus.Unit_18_Hibernate_2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Unit18HibernateApplication {

	public static void main(String[] args) {

//		Content added
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MYSQL_USERNAME", dotenv.get("MYSQL_USERNAME"));
		System.setProperty("MYSQL_PASSWORD", dotenv.get("MYSQL_PASSWORD"));
//		End of content added
		SpringApplication.run(Unit18HibernateApplication.class, args);
	}
}


