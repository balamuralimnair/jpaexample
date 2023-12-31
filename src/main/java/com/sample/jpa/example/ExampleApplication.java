package com.sample.jpa.example;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.sample.jpa.example.service.PurgeService;
import com.sample.jpa.example.service.PurgeServiceImpl;

@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName);
//			}
			PurgeService purgeServiceImpl = ctx.getBean(PurgeService.class);
			purgeServiceImpl.insertData();
			Optional<Integer> purgeDays = Optional.of(0);
			Optional<Integer> pageSize = Optional.of(10);
			purgeServiceImpl.purgeDataByDate(purgeDays,pageSize);

		};
	}

}
