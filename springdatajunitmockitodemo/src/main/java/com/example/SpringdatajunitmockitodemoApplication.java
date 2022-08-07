package com.example;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.example.dto.CustomerDTO;
import com.example.exception.CustomerException;
import com.example.service.CustomerService;

@SpringBootApplication
public class SpringdatajunitmockitodemoApplication implements CommandLineRunner{

	@Autowired
	private CustomerService customerService;

	@Autowired
	private Environment environment;

	public static final  Logger LOGGER = LogManager.getLogger(SpringdatajunitmockitodemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringdatajunitmockitodemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// addCustomer();
		findCustomer();
	    findAllCustomer();
		// updateCustomer();
		// deleteCustomer();

	}

	private void deleteCustomer() {
		try {
			customerService.deleteCustomer(10);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (CustomerException e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	private void updateCustomer() {
		try {
			customerService.updateCustomer(10, "akash01@infy.com");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (CustomerException e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	private void findAllCustomer() {
		try {

			List<CustomerDTO> customerDTOs=customerService.findAll();
			LOGGER.info(customerDTOs);
			
		} catch (CustomerException e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	private void findCustomer() {
		try {
			CustomerDTO customerDTO = customerService.getCustomer(1);
			LOGGER.info(customerDTO);
		} catch (CustomerException e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	private void addCustomer() {
		try {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(10);
			customerDTO.setName("Akash Kumar");
			customerDTO.setEmailId("akash@infy.com");
			customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));
			customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
		} catch (CustomerException e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

}
