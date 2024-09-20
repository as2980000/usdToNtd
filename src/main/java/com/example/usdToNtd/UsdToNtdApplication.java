package com.example.usdToNtd;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class UsdToNtdApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsdToNtdApplication.class, args);
	}

}
