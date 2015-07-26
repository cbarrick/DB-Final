package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;


public class Session {
	
	private Integer SessionsID;
	private java.sql.Timestamp SessionsTS;
	private Integer UserID;

	private static final String URL = "jdbc:mysql://localhost:3306/Blog";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Session() {
		
	}

	public Session(Integer si,java.sql.Timestamp ts, Integer ui) {
		SessionsID = si;
		//userID = ui;
		SessionsTS = ts;
		UserID = ui;
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
	public int getUserID() {
		return UserID;
	}

	public void setUserID(int ui) {
		UserID = ui;
	}

	/*
	 * 
	 *    Create new user
	 */
	public void saveUserSession(Integer sid) throws SQLException{
		Connection conn = null;
		try{
			
			//String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ROOT, ROOTPW);
			if(SessionsID==null){
				try{
					String insertSql = "insert into sessions values(default,?,?)";
					PreparedStatement ps = conn.prepareStatement(insertSql);
					ps.setInt(1,getUserID());
					ps.setTimestamp(2,getSessionsTS());
					
					ps.executeUpdate();	
				}catch(SQLException e){
					System.err.println(e);
				}
			}
			else{
				try{
					String updateSql = "update sessions set Session_ts = ? where UserID = ?";
					PreparedStatement ps = conn.prepareStatement(updateSql);
					ps.setTimestamp(1,getSessionsTS());
					ps.setInt(2,getUserID());
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
			//String URL = "jdbc:mysql://localhost/final_project";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String deleteSql = "delete from sessions where User_Id = ?";
			PreparedStatement ps = conn.prepareStatement(deleteSql);
			ps.setString(1, UserID.toString());
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
	public static Session getSessionByUser(Integer ui) {

		Connection con = null;
		Session c = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "select * from sessions where User_Id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ui.toString());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int si = rs.getInt("Session_Id");
			java.sql.Timestamp ts = rs.getTimestamp("Session_ts");
			int usId = rs.getInt("User_Id");
			c = new Session(si,ts,usId);
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
