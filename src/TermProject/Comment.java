package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Comment {
	
	private Integer commentID;
	private int userID;
	private int postID;
	private String commentText;
	private String timeCommented;
	
	private static final String URL = "jdbc:mysql://localhost:3306/database_page";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Comment() {
		
	}

	private Comment(int ci, int ui, int pi, String ct, String tc) {
		commentID = ci;
		userID = ui;
		postID = pi;
		commentText = ct;
		timeCommented = tc;
	}

	//saves the comment to the database
	public void save() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "INSERT INTO Comments VALUES (";
			if(commentID == null)
				sql += "default, ";
			else
				sql+= getCommentID() + ", ";
			sql += getCommentText() + ", " + getTimeCommented() + ", ";
			sql += getUserID() + getPostID() + ")";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			postID = rs.getInt("Comment_Id");
		} catch (Exception e) {
			System.err.println("Could not save comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment saving connection");
				}
			}
		}
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
	

	// gets the comment with a given id
	public static Comment getCommentByID(int commentID) {

		Connection con = null;
		Comment c = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
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
			System.err.println("Could not get comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close comment get connection");
				}
			}
		}

		return c;

	}

	// get comments associated with a given post
	public static Vector<Comment> getComments(int postID) {

		Connection con = null;
		Vector<Comment> c = new Vector<Comment>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM Comments WHERE Post_Id = " + postID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int ci = rs.getInt("Comment_Id");
				int ui = rs.getInt("User_Id");
				int pi = rs.getInt("Post_Id");
				String text = rs.getString("Description");
				String time = rs.getString("Comment_Date");
				c.addElement(new Comment(ci, ui, pi, text, time));
			}

		} catch (Exception e) {
			System.err.println("Could not get post's comment");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close post's comment get connection");
				}
			}
		}

		return c;

	}
}
