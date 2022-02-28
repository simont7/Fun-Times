package com.ford.henrys.service.unit;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import com.ford.henrys.service.PriceCalculator;
import com.ford.henrys.service.Till;
import com.ford.henrys.service.TillImpl;


@ActiveProfiles("test")
@Configuration
@ComponentScan(basePackages = {"com.ford.henrys.service"})
public class TillUnitTestAppConfig {
	@Bean("till") 
	public Till till() {
		return new TillImpl(priceCalculator());		
	}
	
	@Bean("mockPriceCalculator")
	public PriceCalculator priceCalculator() {
		
		return Mockito.mock(PriceCalculator.class);
	}

}
