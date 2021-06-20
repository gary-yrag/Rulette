package com.init.rulette;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RuletteOnlineApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RuletteOnlineApplication.class, args);
	}
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	return application.sources(RuletteOnlineApplication.class);
}
	
}
