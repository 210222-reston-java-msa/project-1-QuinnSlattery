package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Reimbursement;
import models.User;

/*
 * DAO stands for dsata access object -- the object (in impl form) that we use to access our DB
 * We use the DAO design pattern to separate business logic (java) from our persistence layer
 */
public interface EmployeeDAO {
	// DAO is for CRUD methods 
	public boolean insert(User e); // returns true if successfully inserted	
	public User update(String username, String firstName, String lastName, String email, String password);
	
	public boolean insertReimb(Double amount, String description, String author, int type);
	
	public ArrayList<Reimbursement> findUserReimbursements(String username);
	
	public boolean updateReimbursement(int reimbId, int statusId, String resolver);
	
	public ArrayList<Reimbursement> pendingReimbursements();
	public ArrayList<Reimbursement> resolvedReimbursements();
	public ArrayList<User> findAllEmployees();	
	public List<User> findAll(); // this will return ALL employee objects from the DB
	// we will use this in our service layer and filter it to return JUST one employee that matches
	// username + password
	
	
	public User findByUsername(String username);
	

}