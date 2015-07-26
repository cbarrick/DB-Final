package com.Dbms.Struts2.Demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Session {
	
	private Integer SessionsID;
	private java.sql.Timestamp SessionsTS;
	private String Email;

	private static final String URL = "jdbc:mysql://localhost/final_project";
	private static final String ROOT = "mgadgil09";
	private static final String ROOTPW = "mgadgil09";
	
	public Session() {
		
	}

	public Session(Integer si,java.sql.Timestamp ts, String email) {
		SessionsID = si;
		//userID = ui;
		SessionsTS = ts;
		Email = email;
	}



	public int getSessionsID() {
		return SessionsID;
	}
	public void setSessionsID(Integer sid){
		SessionsID = sid;
	}
	
	
//	public int getUserID() {
//		return userID;
//	}
//	
	public java.sql.Timestamp getSessionsTS() {
		java.util.Date today = new java.util.Date();
		SessionsTS =  new java.sql.Timestamp(today.getTime());
		return SessionsTS;
	}
	
	public void setSessionsTS(java.sql.Timestamp ts) {
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
	public void saveUserSession(Integer sid) throws SQLException{
		Connection conn = null;
		try{
			
			String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, "mgadgil09", "mgadgil09");
			if(SessionsID==null){
				try{
					String insertSql = "insert into sessions values(default,?,?)";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					ps.setTimestamp(1,getSessionsTS());
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
					ps.setTimestamp(1,getSessionsTS());
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
			int si = rs.getInt("Session_Id");
			java.sql.Timestamp ts = rs.getTimestamp("Session_ts");
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
