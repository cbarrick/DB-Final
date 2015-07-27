package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

public class Comment {
	
	private Integer commentID;
	private int userID;
	private int postID;
	private String commentText;
	private Timestamp timeCommented;
	
	private static final String URL = "jdbc:mysql://localhost:3306/Blog";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Comment() {
		
	}

	public Comment(Integer ci, String ct, Timestamp tc,Integer ui, Integer pi) {
		commentID = ci;
		commentText = ct;
		timeCommented = tc;
		userID = ui;
		postID = pi;
		
		
	}

	public Comment(Integer commentID2, String string, int postID2,
			String comment, Timestamp commentDate) {
		// TODO Auto-generated constructor stub
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
			commentID = rs.getInt("Comment_Id");
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
	
	public Timestamp getTimeCommented() {
		return timeCommented;
	}
	
	public void setTimeCommented(Timestamp tc) {
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
			Timestamp time = rs.getTimestamp("timeCommented");
			c = new Comment(ci, text, time, ui, pi);
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
	public static List<Comment> getComments(int postID) {

		Connection con = null;
		List<Comment> c = new ArrayList<Comment>();

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
				Timestamp time = rs.getTimestamp("Comment_Date");
				c.add(new Comment(ci, text, time, ui, pi));
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
	
	public String toString() {
		return this.commentText;
	}
}
