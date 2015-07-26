package com.Dbms.Struts2.Demo;


import com.opensymphony.xwork2.ActionSupport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.interceptor.ParameterAware;


public class CommentAction extends ActionSupport implements ParameterAware {

	private Integer commentID;
	private Integer userID;
	private Integer postID;
	private String commentText;
	private java.sql.Timestamp timeCommented;
	private Map<String,String[]> map;
	
	public Integer getCommentID() {
		return commentID;
	}

	public void setCommentID(Integer id) {
		commentID = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public int getPostID() {
		return postID;
	}

	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public String getComment() {
		return commentText;
	}

	public void setComment(String commentText) {
		this.commentText = commentText;
	}

	public Timestamp getCommentDate() {
		java.util.Date today = new java.util.Date();
		timeCommented =  new java.sql.Timestamp(today.getTime());
		return timeCommented;
	}

	public void setCommentDate(java.sql.Timestamp timeCommented) {
		this.timeCommented = timeCommented;
	}

	public String createComment() {

		
		try {
			
			Comment newComment = new Comment(null, getComment(), getCommentDate(), getUserID(), getPostID());
			newComment.save();
			
		} catch(Exception e) {
			
			System.out.println("Could not create new comment");
			
		}
		

		return SUCCESS;
		
	}

	public void setParameters(Map<String, String[]> pMap) {
		
		userID = Integer.parseInt(map.get("User_Id")[0]);
		postID = Integer.parseInt(map.get("Post_Id")[0]);
		commentText = map.get("Description")[0];
		
	}
	
}
