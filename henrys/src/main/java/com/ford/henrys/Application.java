package com.ford.henrys;

import static java.lang.System.exit;

import java.util.Map;

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

	@Autowired
	private Till till;
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Application.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

	}

	@Override
	public void run(String... args) throws Exception {		
//Run application
		exit(0);
	}
}