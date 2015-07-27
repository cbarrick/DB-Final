package TermProject;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;

public class CreateCommentAction extends BaseAction implements ParameterAware {
	
	private String text;
	private Timestamp commentTime;
	private Integer userId;
	private Integer commentId;
	private Integer postId;
	
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
	
	public void setPostId(int Id)
	{
		this.postId=Id;
	}
	
	public Integer getPostId()
	{
		return postId;
	}
	
	public Timestamp getCommentTime()
	{
		return commentTime;
	}
	
	public void setTimestamp(Timestamp t)
	{
		commentTime = t;	
	}
	
	public void createComment()
	{
		
		Date today = new Date();
		Timestamp ct = new Timestamp(today.getTime());
		Comment comment = new Comment(null, getText(), ct, getCurrentUser().getId(), postId);
		comment.save();
		
	}
	
	@Override
	public void setParameters(Map<String, String[]> map) {
		text = map.get("text")[0];
		postId = Integer.parseInt(map.get("pid")[0]);
	}
	
}
