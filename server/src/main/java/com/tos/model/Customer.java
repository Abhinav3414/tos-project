package com.tos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "Customer")
@Table(name = "customer")
@Where(clause = "is_enable=1")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String contact;
	private String contactPerson;

	private Long managerId;

	private String domain;
	private String address;
	private String country;
	private String expectationFromOpd;
	private String process;
	private String processTools;
	private String solutionProduct;
	private String databaseUsed;
	private String operatingEnvironment;
	private String remoteMachineDetails;
	private String vpnDetails;
	private String communicator;
	private String skillsRequired;
	
	private String imageName;
	
	@Column
	private Boolean isEnable;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Goal.class)
	@JoinColumn(name = "goal_cust_id")
	private List<Goal> goals;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Action.class)
	@JoinColumn(name = "action_cust_id")
	private List<Action> actions;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = StakeHolder.class)
	@JoinColumn(name = "stakeholder_cust_id")
	private List<StakeHolder> stakeHolders;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Team.class)
	@JoinColumn(name = "team_cust_id")
	private List<Team> teams;

	@OneToMany(cascade = CascadeType.ALL, targetEntity = Travel.class)
	@JoinColumn(name = "travel_cust_id")
	private List<Travel> travels;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	public Customer() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long manager) {
		this.managerId = manager;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getExpectationFromOpd() {
		return expectationFromOpd;
	}

	public void setExpectationFromOpd(String expectationFromOpd) {
		this.expectationFromOpd = expectationFromOpd;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getProcessTools() {
		return processTools;
	}

	public void setProcessTools(String processTools) {
		this.processTools = processTools;
	}

	public String getSolutionProduct() {
		return solutionProduct;
	}

	public void setSolutionProduct(String solutionProduct) {
		this.solutionProduct = solutionProduct;
	}

	public String getDatabaseUsed() {
		return databaseUsed;
	}

	public void setDatabaseUsed(String databaseUsed) {
		this.databaseUsed = databaseUsed;
	}

	public String getOperatingEnvironment() {
		return operatingEnvironment;
	}

	public void setOperatingEnvironment(String operatingEnvironment) {
		this.operatingEnvironment = operatingEnvironment;
	}

	public String getRemoteMachineDetails() {
		return remoteMachineDetails;
	}

	public void setRemoteMachineDetails(String remoteMachineDetails) {
		this.remoteMachineDetails = remoteMachineDetails;
	}

	public String getVpnDetails() {
		return vpnDetails;
	}

	public void setVpnDetails(String vpnDetails) {
		this.vpnDetails = vpnDetails;
	}

	public String getCommunicator() {
		return communicator;
	}

	public void setCommunicator(String communicator) {
		this.communicator = communicator;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public List<Goal> getGoals() {
		return goals;
	}

	public void setGoals(List<Goal> goals) {
		this.goals = goals;
	}

	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<StakeHolder> getStakeHolders() {
		return stakeHolders;
	}

	public void setStakeHolders(List<StakeHolder> stakeHolders) {
		this.stakeHolders = stakeHolders;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public List<Travel> getTravels() {
		return travels;
	}

	public void setTravels(List<Travel> travels) {
		this.travels = travels;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
