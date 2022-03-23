package co.ke.jumiaPay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import co.ke.jumiaPay.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	Optional<Customer> findByPhone(String phone);
}
