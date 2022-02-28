package com.ford.henrys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ford.henrys.service.DiscountRulesLoader;
import com.ford.henrys.service.DiscountRulesLoaderImpl;
import com.ford.henrys.service.Discounter;
import com.ford.henrys.service.DiscounterImpl;
import com.ford.henrys.service.PriceCalculator;
import com.ford.henrys.service.PriceCalculatorImpl;
import com.ford.henrys.service.Till;
import com.ford.henrys.service.TillImpl;

@Configuration
@ComponentScan(basePackages = {"com.ford.henrys.service"})
public class AppConfig {
	
	
	@Bean("till")
	public Till till() {
		return new TillImpl(priceCalculator());					
	}
	
	@Bean("priceCalculator") 
	public PriceCalculator priceCalculator() {
		return new PriceCalculatorImpl(discounter());
		
	}
	
	@Bean("discounter")
	public Discounter discounter() {
		
		return new DiscounterImpl(discountLoader());
	}
	
	@Bean("discountRulesLoader")
	public DiscountRulesLoader discountLoader() {
		return new DiscountRulesLoaderImpl();
	}
}
