package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Vector;

public class Post {
	
	
	private String PostTitle;
	private Integer userID;
	private Integer postID;
	private String PostText;
	private Timestamp time;
	
	private static int numPosts = 0;
	private static final String URL = "jdbc:mysql://localhost:3306/Blog";
	private static final String ROOT = "root";
	private static final String ROOTPW = "root123";
	
	public Post() {
		
	}

	public Post(Integer pi, String pt, String Text , int UID, Timestamp tc) {
		postID=pi;
		PostTitle=pt;
		PostText=Text;
		userID=UID;
		time =tc;
	}

	//saves the post to the database
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
			sql += getUserID() + ", " + getTimestamp().toString() + ")";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			postID = rs.getInt("Comment_Id");
			numPosts++;
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
	
	public static int getNumPosts() {
		return numPosts;
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
	
	
	public Timestamp getTimestamp() {
		return time;
	}
	
	public void setTimestamp(Timestamp tc) {
		time = tc;
	}
	

	// gets the post with a given id
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
			Timestamp time = rs.getTimestamp("Post_Date");
			
			c = new Post(pi,pt,Text,UI,time);
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

	// get posts between two dates
	public static Vector<Post> getPostByDate(String from, String totime) {

		Connection con = null;
		Vector<Post> c = new Vector<Post>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM posts WHERE Post_Date > " + from +" and Post_Date < "+ totime;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int pi = rs.getInt("Post_Id");
				String pt = rs.getString("Post_Title");
				String Text = rs.getString("Text");
				int UI = rs.getInt("User_Id");
				Timestamp time = rs.getTimestamp("Post_Date");
				
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
	
	//returns a vector of all post id's
	public static Vector<Integer> getPostIDs() {
		
		Connection con = null;
		Vector<Integer> postIDs = new Vector<Integer>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT Post_Id FROM posts";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				postIDs.addElement(rs.getInt("Post_Id"));
		} catch (Exception e) {
			System.err.println("Could not get post id's");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close post id's connection");
				}
			}
		}

		return postIDs;
		
	}
	
	//returns posts matching a search on post title
	public static Vector<Post> searchTitle(String s) {
		
		Connection con = null;
		Vector<Post> searchResults = new Vector<Post>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, ROOT, ROOTPW);
			String sql = "SELECT * FROM posts WHERE Post_Title LIKE %" + s + "%";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				searchResults.addElement(new Post(rs.getInt("Post_Id"), rs.getString("Post_Title"), rs.getString("Text"), rs.getInt("User_Id"), rs.getTimestamp("Post_Date")));
		} catch (Exception e) {
			System.err.println("Could not search post titles");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					System.err.println("Could not close post search connection");
				}
			}
		}

		return searchResults;
	}
}
