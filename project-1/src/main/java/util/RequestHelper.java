package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.UsernameTemplate;
import models.LoginTemplate;
import models.Reimbursement;
import models.ReimbursementTemplate;
import models.StatusTemplate;
import models.UpdateTemplate;
import models.User;
import repositories.EmployeeDAO;
import repositories.EmployeeDAOImpl;
import services.UserService;

public class RequestHelper {
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
	private static ObjectMapper om = new ObjectMapper();


	public static EmployeeDAO eDao = new EmployeeDAOImpl();
	
	
public static void processPendingReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
	
	ArrayList<Reimbursement> l = eDao.pendingReimbursements();
	
	PrintWriter pw = res.getWriter();
	res.setContentType("application/json");
	
	pw.println(om.writeValueAsString(l));
}

public static void processResolvedReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
	
	ArrayList<Reimbursement> l = eDao.resolvedReimbursements();
	
	PrintWriter pw = res.getWriter();
	res.setContentType("application/json");
	
	pw.println(om.writeValueAsString(l));
}
	
public static void processAllEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
	
	ArrayList<User> l = eDao.findAllEmployees();
	
	PrintWriter pw = res.getWriter();
	res.setContentType("application/json");
	
	pw.println(om.writeValueAsString(l));
}

	
	
public static void processReimbursementChange(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		log.info("process status change in reimbursement-----------------------------------------------------------------------------------------------------------------------------------------");

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);

		StatusTemplate statusChange = om.readValue(body, StatusTemplate.class);

		int reimbId = statusChange.getReimbId();
		int statusId = statusChange.getStatusId();
		String resolver = statusChange.getResolver();
		
		
		boolean t = eDao.updateReimbursement(reimbId, statusId, resolver);
			
		if (t == true) {
			
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			pw.println(om.writeValueAsString(t));

		} else {
			res.setStatus(204); 
		}
		
		
	}
	
	
	
	
public static void processEmployeeReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
log.info("retrieving all user's reimbursements-----------------------------------------------------------------------------------------------------------------------------------------");
		
		// We want to turn whatever we receive as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		

		UsernameTemplate ut = om.readValue(body, UsernameTemplate.class); // from JSON --> Java Object

		
		String username = ut.getUsername();

		ArrayList<Reimbursement> a = eDao.findUserReimbursements(username);
			
		if (a != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("userArrayListReimbursements", a);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(a));

		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
	}
	
	
	
	
	
	
	
	
	public static void processUserReimbursements(HttpServletRequest req, HttpServletResponse res) throws IOException {
log.info("retrieving all user's reimbursements-----------------------------------------------------------------------------------------------------------------------------------------");
		
		// We want to turn whatever we receive as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();

		ArrayList<Reimbursement> a = eDao.findUserReimbursements(username);
			
		if (a != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("userArrayListReimbursements", a);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(a));

		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
	}
	
	
	public static void processEmployeeUpdate(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		log.info("Employee update started");
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();

		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = s.toString();
		log.info(body);

		UpdateTemplate updateAttempt = om.readValue(body, UpdateTemplate.class);

		String username = updateAttempt.getUsername();
		String firstName = updateAttempt.getFirstName();
		String lastName = updateAttempt.getLastName();
		String email = updateAttempt.getEmail();
		String password = updateAttempt.getPassword();

		User u = eDao.update(username, firstName, lastName, email, password);
			
		if (u != null) {
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", u);
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			pw.println(om.writeValueAsString(u));
		} else {
			res.setStatus(204);
		}
	}
	
	
	

	public static void processEmployeeInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		log.info("process employee info-----------------------------------------------------------------------------------------------------------------------------------------");
		
		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();

		log.info("User attempted to login with username: " + username);
		User u = eDao.findByUsername(username);
			
		if (u != null) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", u);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(u));

		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void processReimbursementRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

		log.info("processReimbursementRequest started!");

		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);

		//JSON to Java Object ReimbursementTemplate
		ReimbursementTemplate reimbursementAttempt = om.readValue(body, ReimbursementTemplate.class);

		Double amount = reimbursementAttempt.getAmount();
		String description = reimbursementAttempt.getDescription();
		String author = reimbursementAttempt.getAuthor();
		int typeId = reimbursementAttempt.getTypeId();

		log.info("amount: " + amount + " description " + description + " author " + author + " typeId " + typeId);

		eDao.insertReimb(amount, description, author, typeId);
	}


	
	public static void processLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession(false); // I'm capturing the session, but if there ISN'T one, I don't create a new one.
		
		
		log.info("Processing logout");
		
		
		if (session != null) {
			
			String username = (String) session.getAttribute("username");
			log.info( username + " has logged out");
							
			session.invalidate();
		}
		
		res.setStatus(200);
	
	}
	
	
	
	
public static void processLoginManager(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
		log.info("login-----------------------------------------------------------------------------------------------------------------------------------------");
		
		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();	
		
		log.info("User attempted to login with username: " + username);
		User u = UserService.confirmLogin(username, password);
			
		if (u != null && u.getRoleId()==2) {
			
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(u));

			
			log.info(username + " has successfully logged in");	
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
		
	}
	
	public static void processLoginEmployee(HttpServletRequest req, HttpServletResponse res) throws IOException { 
		
		log.info("login-----------------------------------------------------------------------------------------------------------------------------------------");
		
		// We want to turn whatever we recieve as the request into a string to process
		BufferedReader reader = req.getReader();
		StringBuilder s = new StringBuilder();
		
		// logic to transfer everything from our reader to our string builder
		String line = reader.readLine();
		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = s.toString();
		log.info(body);
		
		
		// I'm going to build a model called LoginTemplate which holds a username and passwrod
		LoginTemplate loginAttempt = om.readValue(body, LoginTemplate.class); // from JSON --> Java Object

		
		String username = loginAttempt.getUsername();
		String password = loginAttempt.getPassword();	
		
		log.info("User attempted to login with username: " + username);
		User u = UserService.confirmLogin(username, password);
			
		if (u != null && u.getRoleId()==1) {
			// get the current session OR create one if it doesn't exist
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			
			// Attaching the print writer to our response
			PrintWriter pw = res.getWriter();
			res.setContentType("application/json");
			
			// this is converting our Java Object (with property firstName!) 
			// to JSON format....that means we can grab the firstName property
			// after we parse it. (We parse it in JavaScript)
			pw.println(om.writeValueAsString(u));

			
			log.info(username + " has successfully logged in");	
		} else {
			res.setStatus(204); // this means that we still have a connection, but no user is found
		}
		
		
	}
	




//------------------------------------------------------------------------------------------------------------------------------------------------------


//------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	// This method's purpose is to return all Employees from the DB in JSON form 
	public static void processEmployees(HttpServletRequest req, HttpServletResponse res) throws IOException {
	
		
		// 1. Set the content type to app/json because we will be sending json data back to the client, 
		// stuck alongside the response
		log.info(UserService.findAll());
		res.setContentType("application/json");
		
		// 2. Get a list of all Employees in the DB
		List<User> allEmps = UserService.findAll();
		
		// 3. Turn the list of Java Objs into a JSON string
		String json = om.writeValueAsString(allEmps);
		
		// 4. Use getWriter() from the response object to return the json string
		PrintWriter pw = res.getWriter();
		pw.println(json);
		
		
	}	
	
	public static void processError(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			req.getRequestDispatcher("error.html").forward(req, res);
			// we do NOT create a new request
			// we also maintain the url....
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	
	}
	

}
