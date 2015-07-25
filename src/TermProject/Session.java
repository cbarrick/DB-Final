package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Session {
	
	private Integer sessionID;
	private java.sql.Date sessionTS;
	private String email;

	private static final String URL = "jdbc:mysql://localhost/final_project";
	private static final String ROOT = "mgadgil09";
	private static final String ROOTPW = "mgadgil09";
	
	public Session() {
		
	}


	public Session(Integer si,java.sql.Date ts, String em) {
		sessionID = si;
		sessionTS = ts;
		email = em;
	}

	public void save() {
		Connection con = null;

		String sql = "INSERT INTO Session VALUES ("
				+ ((sessionID == null) ? "default" : sessionID) + ","
				+ "," + email
				+ "," + sessionTS
				+ ");";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
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
		sessionID = sid;
	}
	
	public java.sql.Date getSessionsTS() {
		return sessionTS;
	}
	
	public void setSessionsTS(java.sql.Date ts) {
		sessionTS = ts;

	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String em) {
		email = em;
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
			if(sessionID==null){
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
