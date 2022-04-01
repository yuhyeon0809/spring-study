package yylab.findMy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"yylab.findMy.domain"})
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class FindMyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindMyApplication.class, args);
	}

}
