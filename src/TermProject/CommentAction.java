package TermProject;

import java.util.Vector;
import com.opensymphony.xwork2.ActionSupport;

public class CommentAction extends ActionSupport {

	private Integer commentID;
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

	private int userID;
	private int postID;
	private String commentText;
	private java.sql.Date timeCommented;

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

		return SUCCESS;
	}

}
