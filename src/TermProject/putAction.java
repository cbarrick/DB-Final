package TermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;


public class putAction extends ActionSupport implements ParameterAware{

	
	private String text;
	private String title;
	private String postTime;
	private Integer userId;
	private Integer PostId;
	

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(int Id) {
		this.userId=Id;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String t)
	{
		this.text=t;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String t)
	{
		this.title=t;
	}
	
	public void setPostId(int Id)
	{
		this.PostId=Id;
	}
	
	public Integer getPostId()
	{
		return PostId;
	}
	
	public String getPostTime()
	{
		return postTime;
	}
	
	public void setPostTime()//generates a timestamp for postTime
	{
		
		java.sql.Date D= new java.sql.Date(System.currentTimeMillis());
		postTime=D.toString();
		
	}
	
	public Post put()
	{
		Post p=Post.getPostByID(PostId);
		
		p.setPostText(text);
		p.setPostTitle(title);
		p.setUserID(userId);
		setPostTime();
		p.setTimeCommented(getPostTime());
		
		return Post.getPostByID(PostId);
	}
	
	
	
	@Override
	public void setParameters(Map<String, String[]> map) {
		
		PostId = Integer.valueOf(map.get("Post_Id")[0]);
		userId = Integer.valueOf(map.get("User_Id")[0]);
		text = map.get("Text")[0];
		title=map.get("Post_Title")[0];
		
	}
	
	
	
	
}