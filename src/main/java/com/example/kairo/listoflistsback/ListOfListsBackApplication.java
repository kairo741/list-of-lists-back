package com.example.kairo.listoflistsback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class }) // remover temporariamente a geração de token para conexão com a API
public class ListOfListsBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListOfListsBackApplication.class, args);
	}

}
