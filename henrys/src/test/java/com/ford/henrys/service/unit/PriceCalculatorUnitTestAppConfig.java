package com.ford.henrys.service.unit;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import com.ford.henrys.service.Discounter;
import com.ford.henrys.service.PriceCalculator;
import com.ford.henrys.service.PriceCalculatorImpl;

@ActiveProfiles("test")
@Configuration
@ComponentScan(basePackages = {"com.ford.henrys.service"})
public class PriceCalculatorUnitTestAppConfig {
	
	@Bean("priceCalculator") 
	public PriceCalculator priceCalculator() {
		return new PriceCalculatorImpl(discounter());
		
	}
	
	@Bean("mockDiscounter")
	public Discounter discounter() {
		
		return Mockito.mock(Discounter.class);
	}
	
}
