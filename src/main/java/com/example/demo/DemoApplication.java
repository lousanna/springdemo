package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(DemoApplication.class)
                        .run(args);

	}

}