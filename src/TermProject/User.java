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
	//String ret = ERROR;

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
			
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
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
	public static boolean validateUser(String user, String pwd) {
		try {
			
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
			String sql = "SELECT * FROM users WHERE";
			sql+=" Email = ? AND Password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,user);
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
}