package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import util.RequestHelper;





public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(RequestHelper.class);
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		log.info("Front Controller-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		
		final String URI = request.getRequestURI().replace("/project-1/", "");
		
		switch(URI) {
		case "loginEmployee":
			RequestHelper.processLoginEmployee(request, response);
			break;
		case "loginManager":
			RequestHelper.processLoginManager(request, response);
			break;
		case "logout":
			RequestHelper.processLogout(request, response);
			break;
		case "reimbursement":
			RequestHelper.processReimbursementRequest(request, response);
			break;
		case "employeeInfo":
			RequestHelper.processEmployeeInfo(request, response);
			break;
		case "updateInfo":
			RequestHelper.processEmployeeUpdate(request, response);
			break;
		case "employeeReimbursements":
			RequestHelper.processUserReimbursements(request, response);
			break;
		case "approveDeny":
			RequestHelper.processReimbursementChange(request, response);
			break;
		case "pendingReimbursements":
			RequestHelper.processPendingReimbursements(request, response);
			break;
		case "resolvedReimbursements":
			RequestHelper.processResolvedReimbursements(request, response);
			break;
		case "allEmployees":
			RequestHelper.processAllEmployees(request, response);
			break;
		case "singleEmployeeReimbursements":
			RequestHelper.processEmployeeReimbursements(request, response);
		} 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}