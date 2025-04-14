package com.bch.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
//@EnableWebSecurity
@PropertySource({"file:${APPS_PROPS}/Config/MdpSpJpa.properties"})
public class MdpSpJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MdpSpJpaApplication.class, args);
	}

}
