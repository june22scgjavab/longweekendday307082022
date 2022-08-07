package com.example.service;

import java.util.List;

import com.example.dto.CustomerDTO;
import com.example.exception.CustomerException;


public interface CustomerService {

	public void addCustomer(CustomerDTO customer) throws CustomerException;
	public CustomerDTO getCustomer(Integer customerId) throws CustomerException;
	public List<CustomerDTO> findAll() throws CustomerException;
	public void updateCustomer(Integer customerId, String emailId) throws CustomerException;
	public void deleteCustomer(Integer customerId)throws CustomerException;
}
