package TermProject;

import java.util.Map;
import java.util.List;
import org.apache.struts2.interceptor.ParameterAware;

public class ViewPostAction extends BaseAction implements ParameterAware {
	
	private Post post;
	
	public String execute() {
		return "success";
	}
	
	public Post getPost() {
		return post;
	}
	
	public List<Comment> getComments() {
		List<Comment> comments = Comment.getComments(post.getPostID());
		System.out.println(comments.size());
		return comments;
	}
	
	public void setParameters(Map<String, String[]> params) {
		Integer pid = Integer.parseInt(params.get("id")[0]);
		post = Post.getPostByID(pid);
	}
	
}
