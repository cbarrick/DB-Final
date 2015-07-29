package TermProject;

import java.util.Map;
import java.util.Date;
import java.sql.Timestamp;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;

public class CreatePostAction extends BaseAction implements ParameterAware {
	
	private String text;
	private String title;
	private Post post;
	
	public Post getPost() {
		return post;
	}
	
	public String createPost()
	{
		Date today = new Date();
		Timestamp pt = new Timestamp(today.getTime());
		if (title != null && text!= null) {
			post = new Post(null, title, text, getCurrentUser().getId(), pt);
			post.save();	
		}
		return SUCCESS;
	}
	
	@Override
	public void setParameters(Map<String, String[]> map) {
		text = map.get("text")[0];
		title = map.get("title")[0];
	}
	
	
}
	
