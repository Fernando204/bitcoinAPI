package com.example.bitcoin_value;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "bitcoin.api.url=https://min-api.cryptocompare.com/data/price?fsym=BTC&tsyms=BRL",
	"keepAlive.api.url=https://keepserveron.onrender.com"
})
class BitcoinValueApplicationTests {

	@Test
	void contextLoads() {
	}

}
