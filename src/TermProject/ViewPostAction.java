package TermProject;

import java.util.Map;
import org.apache.struts2.interceptor.ParameterAware;

public class ViewPostAction extends BaseAction implements ParameterAware {
	
	private Post post;
	
	public String execute() {
		return "success";
	}
	
	public Post getPost() {
		return post;
	}
	
	public void setParameters(Map<String, String[]> params) {
		Integer pid = Integer.parseInt(params.get("id")[0]);
		post = Post.getPostByID(pid);
	}
	
}
