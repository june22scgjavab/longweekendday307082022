package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exception.CustomerException;
import com.example.repository.CustomerRepository;
import com.example.validator.Validator;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void addCustomer(CustomerDTO customerDTO) throws CustomerException {
		Validator.validate(customerDTO);
		/*
		 * Customer customerSearched =
		 * customerRepository.findById(customerDTO.getCustomerId()).get(); if
		 * (customerSearched != null) { throw new
		 * CustomerException("Service.CUSTOMER_FOUND"); }
		 */
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setName(customerDTO.getName());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customerRepository.save(customer);
	}

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws CustomerException {
		//Optional<Customer> optional = customerRepository.findById(customerId);
		//Customer customer = optional.orElseThrow(() -> new CustomerException("Service.CUSTOMER_NOT_FOUND"));
		Customer customerSearched = customerRepository.findById(customerId).get();
		if (customerSearched == null) {
			throw new CustomerException("Service.CUSTOMER_NOT_FOUND");
		}
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customerSearched.getCustomerId());
		customerDTO.setName(customerSearched.getName());
		customerDTO.setEmailId(customerSearched.getEmailId());
		customerDTO.setDateOfBirth(customerSearched.getDateOfBirth());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> findAll() throws CustomerException {
		List<CustomerDTO> customerDTOList = new ArrayList<>();
		Iterable<Customer> customerList = customerRepository.findAll();
		for (Customer customer : customerList) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setName(customer.getName());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTOList.add(customerDTO);
		}
		if (customerDTOList.isEmpty()) {
			throw new CustomerException("Service.CUSTOMERS_NOT_FOUND");
		}

		return customerDTOList;
	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws CustomerException {
		Customer customerSearched = customerRepository.findById(customerId).get();
		if (customerSearched == null) {
			throw new CustomerException("Service.CUSTOMER_FOUND");
		}
		customerSearched.setEmailId(emailId);
	}

	@Override
	public void deleteCustomer(Integer customerId) throws CustomerException {
		Customer customerSearched = customerRepository.findById(customerId).get();
		if (customerSearched == null) {
			throw new CustomerException("Service.CUSTOMER_NOT_FOUND");
		}
		customerRepository.deleteById(customerId);
	}

}
