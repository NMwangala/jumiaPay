package co.ke.jumiaPay.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.ke.jumiaPay.payload.CustomerDto;
import co.ke.jumiaPay.payload.CustomerResponse;
import co.ke.jumiaPay.service.CustomerService;
import co.ke.jumiaPay.utils.AppConstants;

//@RestController
@Controller
@RequestMapping("/api/v1")
public class CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/info")
	public ResponseEntity<String> info() {
		return new ResponseEntity<>("Service is up and running", HttpStatus.OK);
	}

	@GetMapping("/customers")
	public String getAllCustomers(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "country", defaultValue = AppConstants.DEFAULT_COUNTRY, required = false) String country,
			@RequestParam(value = "state", defaultValue = AppConstants.DEFAULT_STATE, required = false) String state,
			Model model) {

		CustomerResponse customerResponse = customerService.getAllCustomers(pageNo, pageSize, country, state);

		List<CustomerDto> customerList = customerResponse.getCustomerData();

		model.addAttribute("customerList", customerList);
		return "customer";
	}
}
