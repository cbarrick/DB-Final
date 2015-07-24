package com.Dbms.Struts2.Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Session {
	

	private Integer SessionsID;
	//private Integer userID;
	private java.sql.Date SessionsTS;
	private String Email;

	private static final String URL = "jdbc:mysql://localhost/final_project";
	private static final String ROOT = "mgadgil09";
	private static final String ROOTPW = "mgadgil09";
	
	public Session() {
		
	}


	public Session(Integer si,java.sql.Date ts, String email) {
		SessionsID = si;
		//userID = ui;
		SessionsTS = ts;
		Email = email;

	}

	public void save() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "INSERT INTO Comments VALUES (";
			if(sessionID == null)
				sql += "default, ";
			else
				sql+= getSessionID() + ", ";
			sql += getUserID() + ", " + getSessionTS() + ", ";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			sessionID = rs.getInt("Session_Id");
		} catch (Exception e) {
			System.err.println("Could not save session");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close session saving connection");
				}
			}
		}
	}

	public int getSessionID() {
		return sessionID;
	}
	public void setSessionsID(Integer sid){
		SessionsID = sid;
	}
	

	
//	public int getUserID() {
//		return userID;
//	}
//	
	public java.sql.Date getSessionsTS() {
		return SessionsTS;
	}
	
	public void setSessionsTS(java.sql.Date ts) {
		SessionsTS = ts;

	}
	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}


	/*
	 * 
	 *    Create new user
	 */
	public void saveUser() throws Exception{
		Connection conn = null;
		try{
			long time = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(time);
			setSessionsTS(date);
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
			if(SessionsID==null){
				try{
					String insertSql = "insert into sessions values(default,?,?)";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					ps.setDate(1,getSessionsTS());
					ps.setString(2,getEmail());
					ps.executeUpdate();	
				}catch(SQLException e){
					System.err.println(e);
				}
			}
			else{
				try{
					String updateSql = "update sessions set Session_ts = ? where Email = ?";
					PreparedStatement ps = conn.prepareStatement(updateSql);
					ps.setDate(1,getSessionsTS());
					ps.setString(2,getEmail());
					ps.executeUpdate();	
				}catch(Exception e){
					System.err.println("Unable to update Session");
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
 * delete session
 */
	public static void deleteSession(String user) throws Exception{
		Connection conn = null;
		try{
//			long time = System.currentTimeMillis();
//			java.sql.Date date = new java.sql.Date(time);
//			setSessionsTS(date);
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
			String deleteSql = "delete from sessions where Email = ?";
			PreparedStatement ps = conn.prepareStatement(deleteSql);
			ps.setString(1,user);
			ps.executeUpdate();	
		}catch(SQLException se){
			System.err.println(se);
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
	 * get sessionsID with a given Email
	 */
	public static Session getSessionByUser(String user) {


		Connection con = null;
		Session c = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "select * from sessions where Email = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,user);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int si = rs.getInt("SessionsID");

			java.sql.Date ts = rs.getDate("SessionsTS");
			String email = rs.getString("Email");
			c = new Session(si,ts,email);

		} catch (Exception e) {
			System.err.println("Could not get SessionsID");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close Sessions get connection");
				}
			}
		}

		return c;

	}

}
