package com.example;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.exception.CustomerException;
import com.example.repository.CustomerRepository;
import com.example.service.CustomerServiceImpl;

@SpringBootTest
class SpringdatajunitmockitodemoApplicationTests {

	@Mock
	private CustomerRepository customerRepository;

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
	
	
	
	@Test
	void addCustomerValidTest() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(10);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));
		Customer customer = new Customer();
		customer.setCustomerId(10);
		customer.setName("Akash Kumar");
		customer.setEmailId("akash@infy.com");
		customer.setDateOfBirth(LocalDate.of(1980, 10, 20));
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		customerServiceImpl.addCustomer(customerDTO);
	    verify(customerRepository,times(1)).save(customer);
	}

	@Test
	void addCustomerInvalidIdTest1() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(-10);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));
		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_ID", exception.getMessage());
	}

	@Test
	void addCustomerInvalidIdTest2() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(0);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_ID", exception.getMessage());
	}
	
	@Test
	void addCustomerInvalidNameTest1() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("AkashKumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_NAME", exception.getMessage());
	}
	@Test
	void addCustomerInvalidNameTest2() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_NAME", exception.getMessage());
	}
	
	@Test
	void addCustomerInvalidNameTest3() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("akash kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_NAME", exception.getMessage());
	}

	@Test
	void addCustomerInvalidNameTest4() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("AKASH KUMAR");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_NAME", exception.getMessage());
	}
	@Test
	void addCustomerInvalidNameTest5() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar Srivastava Hi");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_NAME", exception.getMessage());
	}
	
	
	@Test
	void addCustomerInvalidEmailTest1() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash123@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_EMAIL", exception.getMessage());
	}
	

	@Test
	void addCustomerInvalidEmailTest2() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash#$$@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_EMAIL", exception.getMessage());
	}
	

	@Test
	void addCustomerInvalidEmailTest3() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@gmail.com");
		customerDTO.setDateOfBirth(LocalDate.of(1980, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_EMAIL", exception.getMessage());
	}
	
	
	@Test
	void addCustomerInvalidDateOfBirthTest1() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.of(2023, 10, 20));

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_DOB", exception.getMessage());
	}
	
	@Test
	void addCustomerInvalidDateOfBirthTest2() throws CustomerException {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(1);
		customerDTO.setName("Akash Kumar");
		customerDTO.setEmailId("akash@infy.com");
		customerDTO.setDateOfBirth(LocalDate.now());

		CustomerException exception = Assertions.assertThrows(CustomerException.class,
				() -> customerServiceImpl.addCustomer(customerDTO));
		Assertions.assertEquals("Validator.INVALID_DOB", exception.getMessage());
	}
	
	
	@Test
	void getCustomerValidTest() throws CustomerException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Akash Kumar");
		customer.setEmailId("akash@infy.com");
		customer.setDateOfBirth(LocalDate.of(1980, 10, 20));
		Optional<Customer> customerOptional = Optional.of(customer);

		Mockito.when(customerRepository.findById(1)).thenReturn(customerOptional);
		CustomerDTO customerDTO2 = customerServiceImpl.getCustomer(1);
		System.out.println(customerDTO2);
		Assertions.assertEquals(customer.getCustomerId(), customerDTO2.getCustomerId());
	}

	@Test
	void getCustomersValidTest() throws CustomerException {
		List<Customer> customerList = new ArrayList<>();

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Akash Kumar");
		customer.setEmailId("akash@infy.com");
		customer.setDateOfBirth(LocalDate.of(1980, 10, 20));
		customerList.add(customer);
		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
		List<CustomerDTO> customerDTOList = customerServiceImpl.findAll();

		Assertions.assertEquals(customerList.get(0).getCustomerId(), customerDTOList.get(0).getCustomerId());
	}
	
	
	@Test
	void  updateCustomerValidTest() throws CustomerException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Akash Kumar");
		customer.setEmailId("akash@infy.com");
		customer.setDateOfBirth(LocalDate.of(1980, 10, 20));
		Optional<Customer> customerOptional = Optional.of(customer);

		Mockito.when(customerRepository.findById(1)).thenReturn(customerOptional);
		customerServiceImpl.updateCustomer(1,"akash01@infy.com");
		   verify(customerRepository,times(1)).findById(1);
	
	}


	
	
	@Test
	void  deleteCustomerValidTest() throws CustomerException {
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Akash Kumar");
		customer.setEmailId("akash@infy.com");
		customer.setDateOfBirth(LocalDate.of(1980, 10, 20));
		Optional<Customer> customerOptional = Optional.of(customer);

		Mockito.when(customerRepository.findById(1)).thenReturn(customerOptional);
	
		customerServiceImpl.deleteCustomer(1);
		   verify(customerRepository,times(1)).findById(1);
	
	}
	

}
