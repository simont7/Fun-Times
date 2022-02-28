package com.ford.henrys;

import static java.lang.System.exit;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.ford.henrys.service.Till;


/**
 * Application - to run as executable jar and invoke {@link Till} implementation
 * @author snjohnson
 *
 */

@SpringBootConfiguration
@ComponentScan(basePackages = {"com.ford.henrys.service"})
public class Application implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(Application.class);

	Product SOUP = new Product("Soup", Unit.TIN);
	Product BREAD = new Product("Bread", Unit.LOAF);
	Product MILK = new Product("Milk", Unit.BOTTLE);
	Product APPLES = new Product("Apples", Unit.SINGLE);
	
	StockItem SOUP_ITEM = new StockItem(SOUP, BigDecimal.valueOf(0.65));
	StockItem BREAD_ITEM = new StockItem(BREAD, BigDecimal.valueOf(0.8));
	StockItem APPLES_ITEM = new StockItem(APPLES, BigDecimal.valueOf(0.1));
	StockItem MILK_ITEM = new StockItem(MILK, BigDecimal.valueOf(1.3));
	
	@Autowired
	private Till till;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {	
		
	    // create a scanner so we can read the command-line input
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Henrys Groceries...");
	    System.out.println("\n\nEnter exit to quit");
	    
	    System.out.println("Enter \"product:quantity:no of days\" to add to the basket");
	    System.out.println("e.g. SOUP:2:5 to buy two soups in 5 days time");
	    
	    System.out.println("Enter \"check\" to checkout");
	    
	    String command = "";
	    
	    Basket basket = new Basket();
	    while (command.compareToIgnoreCase("exit") !=0) {
	    	
	    	System.out.println("Enter command: ");
	    	command = scanner.next();
	    			
	    	if (command.compareToIgnoreCase("exit") !=0) {
	    		if (command.compareToIgnoreCase("check") == 0) {
	    			TillReceipt receipt = till.checkout(basket);
	    			System.out.println(receipt.toString());
	    			basket.empty();
	    		}
	    		else {
	    			StringTokenizer tokenizer = new StringTokenizer(command, ":");
	    			basket.addItem(
	    					getProduct(tokenizer.nextToken()),
	    					Integer.valueOf(tokenizer.nextToken()).intValue(),
	    					LocalDate.now().plusDays(Integer.valueOf(tokenizer.nextToken())));
	    		}
	    	}	
	    }
	    scanner.close();
		exit(0);	    
	}

	private StockItem getProduct(String token) {

		if (token.compareToIgnoreCase("SOUP")==0) {
			return SOUP_ITEM;
		}
		else if (token.compareToIgnoreCase("BREAD")==0) {
			return BREAD_ITEM;
		}
		else if (token.compareToIgnoreCase("MILK")==0) {
			return MILK_ITEM;
		}
		else if (token.compareToIgnoreCase("APPLES")==0) {
			return APPLES_ITEM;
		}
		return null;

	}
}