package com.example.bitcoin_value;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.bitcoin_value.repositories.BtcRepository;
import com.example.bitcoin_value.services.BitcoinValue;

@SpringBootApplication
@EnableScheduling
public class BitcoinValueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcoinValueApplication.class, args);
	}

}
