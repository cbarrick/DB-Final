package com.Dbms.Struts2.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class UserModel extends ActionSupport {

	private String user;
	private String password;
	private int Id;
	private String Role;
	private long timeStamp;
	Connection conn = null;
	String ret = ERROR;

	public UserModel() throws Exception{
		try{
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * 
	 *    Create new user
	 */
	public void createUser() throws Exception{
		try{
			String insertSql = "insert into users values(default,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(insertSql);
			ps.setString(1,user);
			ps.setString(2,password);
			ps.setString(3,Role);
			ps.setDate(4,new java.sql.Date(getTimeStamp()));
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
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
			ps.setString(1, user);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Id = rs.getInt("User_Id");
				ret = SUCCESS;
			}
		} catch (Exception e) {
			ret = ERROR;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}
	public String getRole() {
		return Role;
	}

	public void setRole(String Role) {
		this.Role = Role;
	}
	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long ts) {
		this.timeStamp = ts;
	}
}