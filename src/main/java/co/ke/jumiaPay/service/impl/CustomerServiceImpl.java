package co.ke.jumiaPay.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import co.ke.jumiaPay.entity.Customer;
import co.ke.jumiaPay.payload.CustomerDto;
import co.ke.jumiaPay.payload.CustomerResponse;
import co.ke.jumiaPay.repository.CustomerRepository;
import co.ke.jumiaPay.service.CustomerService;
import co.ke.jumiaPay.utils.Util;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private ModelMapper mapper;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper) {
		this.customerRepository = customerRepository;
		this.mapper = mapper;
	}

	@Override
	public CustomerResponse getAllCustomers(int pageNo, int pageSize, String country, String state) {

		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		Page<Customer> listOfCustomers = customerRepository.findAll(pageable);

		List<Customer> customersContent = listOfCustomers.getContent();

		List<CustomerDto> content = customersContent.stream().map(customer -> mapToCustomerDto(customer))
				.collect(Collectors.toList());

		CustomerResponse customerResponse = new CustomerResponse();
		List<CustomerDto> customerDtoWithStateAndCountry = new ArrayList<CustomerDto>();

		for (CustomerDto customerDto : content) {
			customerDto.setState(Util.isValidPhoneNumber(customerDto.getPhone()));
			customerDto.setName(customerDto.getName());
			customerDto.setPhone(customerDto.getPhone());
			customerDto.setCountry(Util.getCountryName(customerDto.getPhone()));
			customerDtoWithStateAndCountry.add(customerDto);
		}
		
		customerResponse.setCustomerData(filterByCountryAndState(country, state, customerDtoWithStateAndCountry));
		customerResponse.setPageNo(listOfCustomers.getNumber());
		customerResponse.setPageSize(listOfCustomers.getSize());
		customerResponse.setTotalElements(listOfCustomers.getTotalElements());
		customerResponse.setTotalPages(listOfCustomers.getTotalPages());
		customerResponse.setLast(listOfCustomers.isLast());
		return customerResponse;
	}

	

	private List<CustomerDto> filterByCountryAndState(String country, String state, List<CustomerDto> customerData) {
		
		
		if(country.isEmpty() && state.isEmpty()) {
			return customerData;
		}else
		if (!country.isEmpty() && !state.isEmpty()) {
			customerData = customerData.stream().filter(customer -> customer.getCountry().equalsIgnoreCase(country)
					&& customer.getState().equalsIgnoreCase(state)).collect(Collectors.toList());
		} else if (!country .isEmpty() || state .isEmpty()) {
			customerData = customerData.stream().filter(customer -> customer.getCountry().equalsIgnoreCase(country))
					.collect(Collectors.toList());
		} else if(country .isEmpty() || !state .isEmpty()) {
			customerData = customerData.stream().filter(customer -> customer.getState().equalsIgnoreCase(state))
					.collect(Collectors.toList());
		}
		return customerData;
	}
	
	

	private CustomerDto mapToCustomerDto(Customer customer) {
		CustomerDto customerDto = mapper.map(customer, CustomerDto.class);
		return customerDto;
	}

	private Customer mapToCustomer(CustomerDto customerDto) {
		Customer customer = mapper.map(customerDto, Customer.class);
		return customer;
	}

	

}
