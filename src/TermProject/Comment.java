package TermProject;

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
	
	public String getCommentText() {
		return commentText;
	}
	
	public void setCommentID(String ct) {
		commentText = ct;
	}
	
	public String getTimeCommented() {
		return timeCommented;
	}
	
	public void setTimeCommented(String tc) {
		timeCommented = tc;
	}
}
