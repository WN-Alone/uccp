package com.nantian.uccp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@MapperScan("com.nantian.uccp.mapper")
public class UccpApplication {

	public static void main(String[] args) {
		SpringApplication.run(UccpApplication.class, args);
	}

}
