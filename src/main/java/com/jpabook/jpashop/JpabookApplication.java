package com.jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;

@SpringBootApplication
public class JpabookApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpabookApplication.class, args);
	}
	
	
	@Bean
	Hibernate5Module hibernate5Module() {
		Hibernate5Module hibernate5Module = new Hibernate5Module();
		hibernate5Module.configure(Feature.FORCE_LAZY_LOADING, false);
		return hibernate5Module;
	}

}
