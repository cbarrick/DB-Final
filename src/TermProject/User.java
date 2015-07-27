package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;


public class User {

	private String user;
	private String password;
	private Integer Id;
	private String Role;
	private java.sql.Timestamp timeStamp;
	static Connection conn = null;
	
	private static final String URL = "jdbc:mysql://localhost:3306/Blog";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";

	public User(Integer id, String email, String pwd, String role, java.sql.Timestamp ts){
		Id = id;
		user = email;
		password = pwd;
		Role = role;
		timeStamp = ts;
	}
	/*
	 * 
	 *    Create new user
	 */
	public void saveUser() throws Exception{
		try{
			
			String URL = "jdbc:mysql://localhost:3306/Blog";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "root", "root123");
			if(Id==null){
				try{
					String insertSql = "insert into users values(default,?,?,'Guest',NOW())";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					ps.setString(1,getUser());
					ps.setString(2,getPassword());
					//ps.setString("Guest");
					//ps.setTimestamp(4,getTimestamp());
					ps.executeUpdate();	
				}catch(SQLException e){
					System.err.println("Unique Constraint violated");
				}catch(Exception e){
					System.err.println("Unable to create User");
				}
			}
			else{
				try{
					String updateSql = "update users set Email=?, Password = ?, Role = ?, Signup_Date = ? where Id = ?";
					PreparedStatement ps = conn.prepareStatement(updateSql);
					ps.setString(1,getUser());
					ps.setString(2,getPassword());
					ps.setString(3,getRole());
					ps.setTimestamp(4,getTimestamp());
					ps.setInt(5,getId());
					ps.executeUpdate();	
				}catch(Exception e){
					System.err.println("Unable to update User");
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("Could not close connection");
				}
			}
		}
	}

	/*
	 * Login credentials validation
	 */
	public static boolean validateUser(Integer userID, String pwd) {
		try {
			
			String URL = "jdbc:mysql://localhost:3306/Blog";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM users WHERE";
			sql+=" User_Id = ? AND Password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,userID.toString());
			ps.setString(2,pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			return true;
		} catch (Exception e) {
			System.err.println("Invalid Email or Password");
			//ret = ERROR;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("Could not close connection");
				}
			}
		}
		return false;
		
		
	}
	
	public static User getUserById(int id) {
		try {	
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM users WHERE";
			sql+=" User_Id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			User newUser = new User(rs.getInt("User_Id"),rs.getString("Email"),rs.getString("Password"),rs.getString("Role"),rs.getTimestamp("SignUp_Date"));
			return newUser;
			
		} catch (Exception e) {
			System.err.println("Invalid Email");
			//ret = ERROR;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("Could not close connection");
				}
			}
		}
		return null;
	}
	
/*return user id from user
 * 
 *
 */
	public static User getUserByName(String user) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM users WHERE";
			sql+=" Email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			User newUser = new User(rs.getInt("User_Id"),user,rs.getString("Password"),rs.getString("Role"),rs.getTimestamp("SignUp_Date"));
			return newUser;
			
		} catch (Exception e) {
			System.err.println("Invalid Email");
			//ret = ERROR;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("Could not close connection");
				}
			}
		}
		return null;
		
		
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}
	public String getRole() {
		return Role;
	}

	public void setRole(String Role) {
		this.Role = Role;
	}

	public java.sql.Timestamp getTimestamp() {
		java.util.Date today = new java.util.Date();
		timeStamp =  new java.sql.Timestamp(today.getTime());
		return timeStamp;
	 
	}
	public void setTimeStamp(java.sql.Timestamp date) {
		this.timeStamp = date;
	}
	
	public String toString() {
		return user;
	}
}
