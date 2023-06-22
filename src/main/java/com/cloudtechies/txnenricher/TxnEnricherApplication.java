package com.cloudtechies.txnenricher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TxnEnricherApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxnEnricherApplication.class, args);
	}

}
