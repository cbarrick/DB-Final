package TermProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class User extends ActionSupport {

	private String user;
	private String password;
	private Integer Id;
	private String Role;
	private java.sql.Date timeStamp;
	Connection conn = null;
	String ret = ERROR;

	public User(Integer id, String email, String pwd, String role, java.sql.Date ts){
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
			long time = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(time);
			setTimeStamp(date);
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
			if(Id==null){
				try{
					String insertSql = "insert into users values(default,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					ps.setString(1,getUser());
					ps.setString(2,getPassword());
					ps.setString(3,getRole());
					ps.setDate(4,getTimeStamp());
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
					ps.setDate(4,getTimeStamp());
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
	public String validateUser() {
		try {

			String sql = "SELECT * FROM users WHERE";
			sql+=" Email = ? AND Password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, getUser());
			ps.setString(2, getPassword());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				setId(rs.getInt("User_Id"));
				ret = SUCCESS;
			}
		} catch (Exception e) {
			System.err.println("Invalid Email or Password");
			ret = ERROR;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					System.err.println("Could not close connection");
				}
			}
		}
		return ret;
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
	public java.sql.Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(java.sql.Date ts) {
		this.timeStamp = ts;
	}
}