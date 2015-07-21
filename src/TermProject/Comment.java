package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Comment {
	
	private int commentID;
	private int userID;
	private int postID;
	private String commentText;
	private String timeCommented;
	
	public Comment() {
		
	}
	
	public Comment(int ci, int ui, int pi, String ct, String tc) {
		commentID = ci;
		userID = ui;
		postID = pi;
		commentText = ct;
		timeCommented = tc;
	}

	// creates a new comment and updates the database
	public static void createComment(Comment c) throws SQLException {
		Connection con = null;

		try {
			String URL = "jdbc:mysql://localhost:3306/database_page";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "rootpassword");
			String sql = "INSERT INTO Comments VALUES (" + c.getCommentID();
			sql += ", " + c.getCommentText() + ", " + c.getTimeCommented() + ", ";
			sql += c.getUserID() + c.getPostID() + ")";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeQuery();
		} catch (Exception e) {
			System.err.println("Could not create comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment creation connection");
				}
			}
		}
	}

	// gets the comment with a given id
	public static Comment getComment(int commentID) {

		Connection con = null;
		Comment c = null;

		try {
			String URL = "jdbc:mysql://localhost:3306/database_page";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "rootpassword");
			String sql = "SELECT * FROM Comments WHERE Comment_Id = " + commentID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			// these will have to be updated with actual column names
			int ci = rs.getInt("commentID");
			int ui = rs.getInt("userID");
			int pi = rs.getInt("postID");
			String text = rs.getString("commentText");
			String time = rs.getString("timeCommented");
			c = new Comment(ci, ui, pi, text, time);
		} catch (Exception e) {
			System.err.println("Could not create comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment creation connection");
				}
			}
		}

		return c;

	}

	// updates comment at commentID with new text
	public static void updateComment(int commentID, String text) {

		Connection con = null;

		try {
			String URL = "jdbc:mysql://localhost:3306/database_page";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "rootpassword");
			String sql = "UPDATE Comments SET Description = " + text + " ";
			sql += "WHERE Comment_Id = " + commentID;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeQuery();
		} catch (Exception e) {
			System.err.println("Could not create comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment creation connection");
				}
			}
		}
	}

	// get comments associated with a given post
	public static Vector<Comment> getComments(int postID) {

		Connection con = null;
		Vector<Comment> c = new Vector<Comment>();

		try {
			String URL = "jdbc:mysql://localhost:3306/database_page";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, "root", "rootpassword");
			String sql = "SELECT * FROM Comments WHERE Post_Id = " + postID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// these will have to be updated with actual column names
				int ci = rs.getInt("commentID");
				int ui = rs.getInt("userID");
				int pi = rs.getInt("postID");
				String text = rs.getString("commentText");
				String time = rs.getString("timeCommented");
				c.addElement(new Comment(ci, ui, pi, text, time));
			}

		} catch (Exception e) {
			System.err.println("Could not create comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment creation connection");
				}
			}
		}

		return c;

	}
	
	public int getCommentID() {
		return commentID;
	}
	
	public void setUserID(int ui) {
		userID = ui;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setPostID(int pi) {
		postID = pi;
	}
	
	public int getPostID() {
		return postID;
	}
	
	public void setCommentText(String ct) {
		commentText = ct;
	}
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentID(String ci) {
		commentText = ci;
	}
	
	public String getTimeCommented() {
		return timeCommented;
	}
	
	public void setTimeCommented(String tc) {
		timeCommented = tc;
	}
}
