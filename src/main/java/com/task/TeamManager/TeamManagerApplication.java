package com.task.TeamManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.task.TeamManager")
public class TeamManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamManagerApplication.class, args);
	}

}
