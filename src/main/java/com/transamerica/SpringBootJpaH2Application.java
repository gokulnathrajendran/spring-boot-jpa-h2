package com.transamerica;

import com.transamerica.model.Movie;
import com.transamerica.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class SpringBootJpaH2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJpaH2Application.class, args);
	}

	//TODO: Remove this in Production Code (For Internal USE ONLY)
	//Inserting Demo Datas
	@Bean
	public CommandLineRunner movieDemo(MovieRepository movieRepository){
		return (args)->{
		//create movie
		movieRepository.save(new Movie("WonderWomen","1982 Movie",true));
		movieRepository.save(new Movie("Batman","The Protector of GowthamCity",false));
		};
	}

}
