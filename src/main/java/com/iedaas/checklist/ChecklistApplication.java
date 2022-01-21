package com.iedaas.checklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class ChecklistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistApplication.class, args);
	}

}
