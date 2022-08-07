package com.example.validator;

import java.time.LocalDate;

import com.example.dto.CustomerDTO;
import com.example.exception.CustomerException;


public class Validator {

	
	public static void validate(CustomerDTO customerDTO) throws CustomerException
	{
		String message=null;
		if(!validateCustomerId(customerDTO.getCustomerId()))
		{
			message="Validator.INVALID_ID";
		}
		if(!validateCustomerEmail(customerDTO.getEmailId()))
		{
			message="Validator.INVALID_EMAIL";
		}
		if(!validateCustomerName(customerDTO.getName()))
		{
			message="Validator.INVALID_NAME";
		}
		
		if(!validateCustomerDateOfBirth(customerDTO.getDateOfBirth()))
		{
			message="Validator.INVALID_DOB";
		}
	 if(message!=null) {
		 throw new CustomerException(message);
	 }
	
	}

	private static boolean validateCustomerDateOfBirth(LocalDate dateOfBirth) {
		
		return dateOfBirth.isBefore(LocalDate.now()) ? true : false;
	}

	private static boolean validateCustomerName(String name) {
		
		return name.matches("^([A-Z][a-z]+)(\\s[A-Z][a-z]+){1,2}$") ? true : false;
				
	}

	private static boolean validateCustomerEmail(String emailId) {
		return emailId.matches("^[a-z0-1]+@infy\\.com$") ? true : false;
	}

	private static boolean validateCustomerId(int customerId) {
		
		return customerId<=0?false : true;
	}
}
