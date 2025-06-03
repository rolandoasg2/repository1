package com.bch.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
//@EnableWebSecurity
//@PropertySource("file:/opt/apps/config/ApiSecurity.properties")
@PropertySource({"file:${APPS_PROPS}/Config/ApiSecurity.properties"})
public class ApiSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityApplication.class, args);
	}

}
