package repositories;

import java.util.ArrayList;
import java.util.List;

import models.Reimbursement;
import models.User;

public class Test {
	
	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	public static void main(String[] args) {
		
		ArrayList<User> testing = eDao.findAllEmployees();
		System.out.println(testing);
}
}