package com.nwjshm.multitenancy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "com.nwjshm.multitenancy.**.mapper"})
public class MultiTenancyDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiTenancyDemoApplication.class, args);
	}

}
