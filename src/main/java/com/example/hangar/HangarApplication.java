package com.example.hangar;

import com.example.hangar.entity.Car;
import com.example.hangar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

@EnableAutoConfiguration
@Configuration
@SpringBootApplication
@ComponentScan({"com.example.hangar", "controllers", "service"})
public class HangarApplication {

	public static void main(String[] args) {
		SpringApplication.run(HangarApplication.class, args);

	}
}
