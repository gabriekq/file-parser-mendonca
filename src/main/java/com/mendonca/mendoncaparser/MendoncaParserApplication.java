package com.mendonca.mendoncaparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mendonca.modell.Person;
import com.mendonca.modell.PersonFile;
import com.mendonca.repository.FilePersonRepository;
import com.mendonca.repository.PageablePersonRepo;
import com.mendonca.repository.PersonRepository;





@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = {PersonRepository.class,FilePersonRepository.class,PageablePersonRepo.class})
@EntityScan(basePackageClasses =  {Person.class,PersonFile.class})
@ComponentScan(basePackages = {"com.mendonca.controller","com.mendonca.service"})
public class MendoncaParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MendoncaParserApplication.class, args);
	}

}
