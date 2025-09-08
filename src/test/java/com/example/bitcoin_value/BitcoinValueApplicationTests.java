package com.example.bitcoin_value;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "bitcoin.api.url=https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=brl"
})
class BitcoinValueApplicationTests {

	@Test
	void contextLoads() {
	}

}
