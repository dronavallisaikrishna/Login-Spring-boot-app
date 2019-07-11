package com.learnovercoffee.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.learnovercoffee.Login.configurations.AppProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

}
