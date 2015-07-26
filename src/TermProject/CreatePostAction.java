package TermProject;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;


public class CreatePostAction extends ActionSupport implements ParameterAware {
	
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
	
	
	public Post Post()
	{
		Integer i=null;
		Post P= new Post();
		P.setPostText(getText());
		P.setPostTitle(getTitle());
		P.setUserID(getUserId());
		P.setPostID(i);
		setPostTime();
		P.setTimeCommented(getPostTime());
		
		P.save();
		
		return P;
	}
	
	@Override
	public void setParameters(Map<String, String[]> map) {
		
		userId = Integer.parseInt(map.get("User_Id")[0]);
		text = map.get("Text")[0];
		title=map.get("Post_Title")[0];

		
	}
	
	
}
	
