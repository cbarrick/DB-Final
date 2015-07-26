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


public class getPostByIdAction extends ActionSupport implements ParameterAware {
	
	private String text;
	private String title;
	private Integer userId;
	private Integer PostId;
	private java.sql.Date postTime;
	
	
	
	
	public Post getPostByIdAction()
	{
		
		
		
		return Post.getPostByID(PostId);
		
		
	}
	
	
	public static Post getPostByIdAction(Integer PID)
	{
		
		
		return Post.getPostByID(PID);
		

	}
	
	@Override
public void setParameters(Map<String, String[]> map) {
		
		PostId= Integer.valueOf(map.get("Post_Id")[0]);

		
	}
	
	
}