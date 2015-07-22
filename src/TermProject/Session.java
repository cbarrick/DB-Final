package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class Session {
	
	private Integer sessionID;
	private int userID;
	private String sessionTS;

	private static final String URL = "jdbc:mysql://localhost:3306/database_page";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Session() {
		
	}

	public Session(int si, int ui, String ts) {
		sessionID = si;
		userID = ui;
		sessionTS = ts;
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
	
	
	public int getUserID() {
		return userID;
	}
	
	public String getSessionTS() {
		return sessionTS;
	}
	
	public void setSessionTS(String ts) {
		sessionTS = ts;
	}


	//get sessionsID with a given userID
	public static Session getSessionBYID(int userID) {

		Connection con = null;
		Session c = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM Sessions WHERE User_Id = " + userID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();

			int si = rs.getInt("SessionsID");
			int ui = rs.getInt("userID");
			String ts = rs.getString("SessionsTS");
			c = new Session(si, ui, ts);
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
