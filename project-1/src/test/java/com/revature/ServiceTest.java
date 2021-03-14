package com.revature;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.User;
import repositories.EmployeeDAOImpl;
import services.UserService;

public class ServiceTest {

	// class to be tested
	private UserService userv;
	
	// dependencies that our service layer needs to make contact with DB
	// later we will MOCK this and trick our application into thinking it's connecting to a DB when it's not
	private EmployeeDAOImpl edaoImpl;
	
	
	@Before
	public void setUp() throws Exception {
		userv = new UserService();
		
		/*
		 * Here is where we are creating a fake DB connection
		 * and tricking our service layer
		 */
		
		edaoImpl = mock(EmployeeDAOImpl.class);
		
		// here we set the EmployeeDAOImpl OF the service layer to the one we mocked
	
		userv.eDao = edaoImpl;
	
	}

	/*
	 * This happy path test is a default scenario in which we
	 * allow it input and get expected output without exceptions
	 */
	
	@Test
	public void happyPathScenario() {
		
		User sampleUser = new User(2, "userq", "s", "a", "b", "email", 1);
		List<User> list = new ArrayList<User>();
		list.add(sampleUser);
		
		//programming our fake dao to return this as its fake data
		when(edaoImpl.findAll()).thenReturn(list);
		
		User foundByUsername = userv.findByUsername(sampleUser.getUsername());

		assertEquals(sampleUser, foundByUsername);
	}
	
	@Test
	public void employeeIsNotPresentInDB() {
		
		List<User> emptyList = new ArrayList<User>();
		
		when(edaoImpl.findAll()).thenReturn(emptyList);
		
		User foundByUser = userv.findByUsername("dingus");
		
		assertEquals(null, foundByUser);
	}

}
