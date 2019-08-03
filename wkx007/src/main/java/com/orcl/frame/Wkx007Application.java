package com.orcl.frame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.orcl.frame.dao")
@EnableSwagger2
public class Wkx007Application {

	public static void main(String[] args) {
		SpringApplication.run(Wkx007Application.class, args);
	}

}
