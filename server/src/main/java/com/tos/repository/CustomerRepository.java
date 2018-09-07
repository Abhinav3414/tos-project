package com.tos.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tos.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByManagerId(Long managerId);
/*
	 @Query("SELECT t FROM Todo t where t.title = :title AND t.description = :description")
	    public Optional<Todo> findByTitleAndDescription(@Param("title") String title, 
	                                                    @Param("description") String description);
	     
	 */
}
