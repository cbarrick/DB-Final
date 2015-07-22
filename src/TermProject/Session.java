package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class Sessions {
	
	private Integer SessionsID;
	private int userID;
	private String SessionsTS;

	private static final String URL = "jdbc:mysql://localhost:3306/database_page";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Sessions() {
		
	}

	public Sessions(int si, int ui, String ts) {
		SessionsID = si;
		userID = ui;
		SessionsTS = ts;
	}



	public int getSessionsID() {
		return SessionsID;
	}
	
	
	public int getUserID() {
		return userID;
	}
	
	public String getSessionsTS() {
		return SessionsTS;
	}
	
	public void setSessionsTS(String ts) {
		SessionsTS = ts;
	}


	//get sessionsID with a given userID
	public static Sessions getSessionsBYID(int userID) {

		Connection con = null;
		Sessions c = null;

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
			c = new Sessions(si, ui, ts);
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
