package com.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.SneakyThrows;

@SpringBootApplication
public class TxApplication {

	@SneakyThrows
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context=SpringApplication.run(TxApplication.class, args);
		for(;;){
			int _char=System.in.read();
			if(_char=='q'){
				break;
			}
		}
		context.close();
	}
}
