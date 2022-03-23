package co.ke.jumiaPay.service;

import co.ke.jumiaPay.payload.CustomerDto;
import co.ke.jumiaPay.payload.CustomerResponse;

public interface CustomerService {
	
	CustomerResponse getAllCustomers(int pageNo, int pageSize, String country, String state);
	
}
