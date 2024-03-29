package com.tos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tos.model.Goal;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	List<Goal> getGoalByCustomerId(Long customerId);
}
