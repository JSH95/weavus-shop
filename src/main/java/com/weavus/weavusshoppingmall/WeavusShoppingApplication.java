package com.weavus.weavusshoppingmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value = {""})
public class WeavusShoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeavusShoppingApplication.class, args);
	}

}
