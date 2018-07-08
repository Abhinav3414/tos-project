package com.tos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tos.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByManagerId(Long managerId);

}
