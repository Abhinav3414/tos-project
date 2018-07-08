package com.tos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tos.model.Customer;
import com.tos.model.Goal;
import com.tos.model.StakeHolder;
import com.tos.model.Team;
import com.tos.model.Travel;
import com.tos.model.User;
import com.tos.repository.CustomerRepository;
import com.tos.repository.GoalRepository;
import com.tos.repository.StakeHolderRepository;
import com.tos.repository.TeamRepository;
import com.tos.repository.TravelRepository;
import com.tos.repository.UserRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private GoalRepository goalRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private StakeHolderRepository stakeHolderRepository;

	@Autowired
	private TravelRepository travelRepository;

	@Autowired
	private UserRepository userRepository;

	/* search all customers */
	public List<Customer> getAllCustomer() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> currentUser = userRepository.findByUsername(auth.getName());

		if (currentUser.isPresent()) {
			User user = currentUser.get();

			if (user.getRoles().stream().filter(r -> r.getName().equals("ADMIN")).count() > 0) {
				return customerRepository.findAll();
			}
			if (user.getRoles().stream().filter(r -> r.getName().equals("MANAGER")).count() > 0) {
				return customerRepository.findByManagerId(user.getId());
			}
		}
		return null;
	}

	/* to save an customer */
	public Customer addCustomer(Customer cust) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Optional<User> currentUser = userRepository.findByUsername(auth.getName());

		if (currentUser.isPresent()) {
			User user = currentUser.get();

			if (user.getRoles().stream().filter(r -> r.getName().equals("ADMIN")).count() > 0) {
				cust.setManagerId(0L);
				cust.setIsEnable(true);
				return customerRepository.save(cust);
			}
			if (user.getRoles().stream().filter(r -> r.getName().equals("MANAGER")).count() > 0) {
				cust.setManagerId(user.getId());
				cust.setIsEnable(true);
				return customerRepository.save(cust);
			}
		}
		return null;
	}

	/* get an customer by id */
	public Customer getCustomer(Long custId) {
		return customerRepository.findOne(custId);
	}

	/* to update an customer */
	public Customer updateCustomer(Customer cust, long id) {
		if (customerRepository.findOne(id) == null) {
			return null;
		}
		return customerRepository.save(cust);
	}

	/* delete an customer */
	public boolean deleteCustomer(Long custId) {

		Customer cust = customerRepository.findOne(custId);

		if (cust == null) {
			return false;
		}
		List<Goal> customerGoals = goalRepository.getGoalByCustomerId(custId);
		List<Team> customerTeams = teamRepository.getTeamByCustomerId(custId);
		List<StakeHolder> customerStakeHolders = stakeHolderRepository.getStakeHolderByCustomerId(custId);
		List<Travel> customerTravels = travelRepository.getTravelByCustomerId(custId);

		for (Goal goal : customerGoals) {
			goalRepository.delete(goal.getId());
		}
		for (Team team : customerTeams) {
			teamRepository.delete(team.getId());
		}
		for (StakeHolder stakeHolder : customerStakeHolders) {
			stakeHolderRepository.delete(stakeHolder.getId());
		}
		for (Travel travel : customerTravels) {
			travelRepository.delete(travel.getId());
		}

		cust.setIsEnable(false);
		customerRepository.save(cust);
		// customerRepository.delete(custId);

		return true;
	}

}
