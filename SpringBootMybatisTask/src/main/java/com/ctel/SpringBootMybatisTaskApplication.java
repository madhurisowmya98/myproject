
package com.ctel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages ="com.ctel.dao")
public class SpringBootMybatisTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisTaskApplication.class, args);
	}

}
