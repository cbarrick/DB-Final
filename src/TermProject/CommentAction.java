package TermProject;


import com.opensymphony.xwork2.ActionSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;
import org.apache.struts2.interceptor.ParameterAware;


public class CommentAction extends ActionSupport implements ParameterAware {

	private Integer commentID;
	private int userID;
	private int postID;
	private String commentText;
	private java.sql.Date timeCommented;
	
	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer id) {
		commentID = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}
	public String getComment() {
		return commentText;
	}

	public void setComment(String commentText) {
		this.commentText = commentText;
	}

	public java.sql.Date getCommentDate() {
		return timeCommented;
	}

	public void setCommentDate(java.sql.Date timeCommented) {
		this.timeCommented = timeCommented;
	}

	public String CreateComment() {

		try{
			if (userID==null) {
				Comment newComment = new Comment(getCommentID(),"Guest",getPostID(),getComment(),getCommentDate());
			} else {
				Comment newComment = new Comment(getCommentID(),getUserID(),getPostID(),getComment(),getCommentDate());
			
			}
			newComment.saveComment();
			System.out.println("Your comment was successfully submitted!")
		}catch(Exception){
			System.out.println("Your comment could not be submitted");
		}
		newComment.save();
		return newComment;
	}
	
}
