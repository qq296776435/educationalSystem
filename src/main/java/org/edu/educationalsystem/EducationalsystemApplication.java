package org.edu.educationalsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.edu.educationalsystem.DAO")
public class EducationalsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducationalsystemApplication.class, args);
	}
}
