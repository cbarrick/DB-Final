package TermProject;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;

public class CreatePostAction extends BaseAction {
	
	private String text;
	private String title;
	private Timestamp postTime;
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
	
	public Timestamp getPostTime()
	{
		return postTime;
	}
	
	public void setTimestamp(Timestamp t)
	{
		postTime = t;	
	}
	
	public void createPost()
	{
		
		Date today = new Date();
		Timestamp pt = new Timestamp(today.getTime());
		Post post = new Post(null, getTitle(), getText(), getCurrentUser().getID(), pt);
		post.save();
		
	}
	
	@Override
	public void setParameters(Map<String, String[]> map) {
		
		text = map.get("Text")[0];
		title=map.get("Post_Title")[0];
		
	}
	
	
}
	
