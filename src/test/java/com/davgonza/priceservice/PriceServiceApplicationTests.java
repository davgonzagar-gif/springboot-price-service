package com.davgonza.priceservice;

import com.davgonza.priceservice.application.port.out.PriceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PriceServiceApplicationTests {

	@MockBean
	private PriceRepository priceRepository;

	@Test
	void contextLoads() {
	}
}