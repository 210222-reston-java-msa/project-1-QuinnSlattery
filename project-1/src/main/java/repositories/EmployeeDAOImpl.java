package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import util.ConnectionUtil;
import models.Reimbursement;
import models.User;

// This is wehre we write some statments!
public class EmployeeDAOImpl implements EmployeeDAO {

	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public User findByUsername(String Username) {

		User u = new User();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM users WHERE username = '" + Username + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");

				u = new User(username, password, firstName, lastName, email, roleId);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return u;

	}

	@Override
	public ArrayList<Reimbursement> findUserReimbursements(String username) {

		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM reimbursement WHERE reimb_author = '" + username + "'";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitted = rs.getString("reimb_submitted");
				String resolved = rs.getString("reimb_resolved");
				String description = rs.getString("reimb_description");
				String author = rs.getString("reimb_author");
				String resolver = rs.getString("reimb_resolver");
				int statusId = rs.getInt("reimb_status_id");
				int typeId = rs.getInt("reimb_type_id");

				Reimbursement r = new Reimbursement(reimbId, amount, submitted, resolved, description, author, resolver,
						statusId, typeId);
				list.add(r);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve reimbursements", ex);

		}
		return list;
	}

	public List<User> findAll() {

		List<User> list = new ArrayList<User>();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM users";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");

				User u = new User(username, password, firstName, lastName, email, roleId);
				list.add(u);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}
	
	@Override
	public ArrayList<User> findAllEmployees() {

		ArrayList<User> list = new ArrayList<User>();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM users WHERE role_id = 1";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				int roleId = rs.getInt("role_id");

				User u = new User(username, password, firstName, lastName, email, roleId);
				list.add(u);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve all users", ex);

		}

		return list;
	}

	
	@Override
	public ArrayList<Reimbursement> resolvedReimbursements() {

		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM reimbursement WHERE reimb_status_id = 2 OR reimb_status_id = 3";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitted = rs.getString("reimb_submitted");
				String resolved = rs.getString("reimb_resolved");
				String description = rs.getString("reimb_description");
				String author = rs.getString("reimb_author");
				String resolver = rs.getString("reimb_resolver");
				int statusId = rs.getInt("reimb_status_id");
				int typeId = rs.getInt("reimb_type_id");

				Reimbursement r = new Reimbursement(reimbId, amount, submitted, resolved, description, author, resolver,
						statusId, typeId);
				list.add(r);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve reimbursements", ex);

		}
		return list;
	}
	
	
	
	@Override
	public ArrayList<Reimbursement> pendingReimbursements() {

		ArrayList<Reimbursement> list = new ArrayList<Reimbursement>();

		try {

			Connection conn = ConnectionUtil.getConnection();

			String sql = "SELECT * FROM reimbursement WHERE reimb_status_id = 1";

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int reimbId = rs.getInt("reimb_id");
				double amount = rs.getDouble("reimb_amount");
				String submitted = rs.getString("reimb_submitted");
				String description = rs.getString("reimb_description");
				String author = rs.getString("reimb_author");
				int statusId = rs.getInt("reimb_status_id");
				int typeId = rs.getInt("reimb_type_id");

				Reimbursement r = new Reimbursement(reimbId, amount, submitted, description, author, statusId, typeId);
				list.add(r);
			}

		} catch (SQLException ex) {
			log.warn("Unable to retrieve reimbursements", ex);

		}
		return list;
	}

	@Override
	public boolean insertReimb(Double amount, String description, String author, int type) {

		PreparedStatement stmt = null;
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, amount);
			stmt.setString(2, timeStamp);
			stmt.setString(3, null);
			stmt.setString(4, description);
			stmt.setString(5, author);
			stmt.setString(6, null);
			stmt.setInt(7, 1);
			stmt.setInt(8, type);

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert reimbursement", ex);
			return false;
		}
		// If everything is successful, we return true
		return true;
	}

	public boolean insert2(Reimbursement r) {

		PreparedStatement stmt = null;

		try {

			Connection conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, r.getAmount());
			stmt.setString(2, r.getSubmitted());
			stmt.setString(3, r.getResolved());
			stmt.setString(4, r.getDescription());
			stmt.setString(5, r.getAuthor());
			stmt.setString(6, r.getResolver());
			stmt.setInt(7, r.getStatusId());
			stmt.setInt(8, r.getTypeId());

			if (!stmt.execute()) {
				return false;
			}

		} catch (SQLException ex) {
			log.warn("Unable to insert reimbursement", ex);
			return false;
		}
		// If everything is successful, we return true
		return true;
	}

	@Override
	public User update(String username, String firstName, String lastName, String email, String password) {

		Connection conn = ConnectionUtil.getConnection();
		String sql = "UPDATE project1.users SET first_name = '" + firstName + "', last_name = '" + lastName
				+ "', email = '" + email + "', user_password = '" + password + "' WHERE username = '" + username + "'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		User u = findByUsername(username);
		return u;
	}

	@Override
	public boolean updateReimbursement(int reimbId, int statusId, String resolver) {
		Connection conn = ConnectionUtil.getConnection();
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		String sql = "UPDATE project1.reimbursement SET reimb_status_id = " + statusId + ", reimb_resolver = '" + resolver +"', reimb_resolved = '" + timeStamp + "' WHERE reimb_id = " + reimbId;

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public boolean insert(User e) {
		// TODO Auto-generated method stub
		return false;
	}

}