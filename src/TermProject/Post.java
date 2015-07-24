package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Vector;

public class Post {
	
	
	private String PostTitle;
	private Integer userID;
	private Integer postID;
	private String PostText;
	private String timeCommented;
	
	private static final String URL = "jdbc:mysql://localhost:3306/database_page";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Post() {
		
	}

	private Post(int pi, String pt, String Text , int UID, String tc) {
		postID=pi;
		PostTitle=pt;
		PostText=Text;
		userID=UID;
		timeCommented =tc;
	}

	//saves the comment to the database
	public void save() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "INSERT INTO posts VALUES (";
			if(postID == null)
				sql += "default, ";
			else
				sql+= getPostID() + ", ";
			sql += getPostTitle() + ", " + getPostText() + ", ";
			sql += getUserID() + getTimeCommented() + ")";
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
	
	public int getPostID() {
		return postID;
	}
	
	public void setUserID(int ui) {
		userID = ui;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getPostTitle()
	{
		return PostTitle;
	}
	
	public void setPostTitle(String title){
		PostTitle=title;
	}

	
	//the comments have this but im not sure it is needed
	public void setPostID(int pi) {
		postID = pi;
	}
	
	
	
	public void setPostText(String ct) {
		PostText = ct;
	}
	
	public String getPostText() {
		return PostText;
	}
	
	
	public String getTimeCommented() {
		return timeCommented;
	}
	
	public void setTimeCommented(String tc) {
		timeCommented = tc;
	}
	

	// gets the comment with a given id
	public static Post getPostByID(int PID) {

		Connection con = null;
		Post c = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM posts WHERE Post_Id = " + PID;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			// these will have to be updated with actual column names
			int pi = rs.getInt("Post_Id");
			String pt = rs.getString("Post_Title");
			String Text = rs.getString("Text");
			int UI = rs.getInt("User_Id");
			String time = rs.getString("Post_Date");
			
			c= new Post(pi,pt,Text,UI,time);
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
	public static Vector<Post> getPostByDate(String from, String totime) {

		Connection con = null;
		Vector<Post> c = new Vector<Post>();

		//may want to put from and totime in proper format here ex 1000-09-21
		//java.sql.Date.valueOf(String date) will help put date into proper format
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM posts WHERE Post_Date > " + java.sql.Date.valueOf(from)+ " AND Post_Date < " +java.sql.Date.valueOf(totime);//from and totime must be in the proper yyyy-MM-dd format
		
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int pi = rs.getInt("Post_Id");
				String pt = rs.getString("Post_Title");
				String Text = rs.getString("Text");
				int UI = rs.getInt("User_Id");
				String time = rs.getString("Post_Date");
				
				c.addElement(new Post(pi,pt,Text,UI,time));
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