package com.tos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tos.model.Ace5;

/**
 * This class extends JpaRepository and gives Ace5 fuctionality to use JPA features to update database
 * 
 * @author Abhinav Gupta
 * @version 1.0 
 * @since 15-06-2018
 */

public interface Ace5Repository extends JpaRepository<Ace5, Long> {

	Ace5 getAce5ByCustomerId(Long customerId);
}
