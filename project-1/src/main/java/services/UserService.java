package services;

import java.util.List;

import models.User;
import repositories.EmployeeDAO;
import repositories.EmployeeDAOImpl;


public class UserService {
	
	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	
	// find by username...
		public static User findByUsername(String username) {
			List<User> all = eDao.findAll();
			
			
			for (User u : all) { // filtering with an enhanced for-loop!
				if (u.getUsername().equals(username)) {
					return u; // we return the Employee object with a matching ID
				} else {
					continue;   // if username doesn't match the username prop of the Employee element
								// then continue coninue the loop to the next element.
				}
			}
			
			return null;
		}
	
	
	
	
	public static List<User> findAll() {
		return eDao.findAll();
	}
	
	
	
	// confirm login method
	public static User confirmLogin(String username, String password) {
		
		// we use the above method
		User u = findByUsername(username);
		
		if (u == null) {
			return null;
		}
		
		if (u.getPassword().equals(password)) {
			return u;
		} else {
			return null;
		}
	}
}