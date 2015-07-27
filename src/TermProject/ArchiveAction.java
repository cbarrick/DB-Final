package TermProject;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

public class ArchiveAction extends BaseAction implements ParameterAware {

	static int page;
	final static int PAGE_SIZE = 5;
	
	//get archive page number p. p = 0 gives the 5 most recent posts, p = 1 gives the next 5, etc.
	public List<Post> getPosts() {
		List<Post> posts = new ArrayList<Post>();
		List<Integer> postIDs = Post.getPostIDs();
		
		for (int i = PAGE_SIZE*(page-1); i < PAGE_SIZE*(page); i++) {
			if (i >= postIDs.size()) break;
			posts.add(Post.getPostByID(postIDs.get(i)));
		}
		return posts;
		
	}
	
	public int getTotalPages() {
		return (int) Math.ceil(Post.getNumPosts()/PAGE_SIZE);
		
	}

	//only parameter to be passed is called Page_Number
	public void setParameters(Map<String, String[]> map) {
		String pageStr = map.get("p")[0];
		page = Integer.parseInt(pageStr);
	}
	
	public String getPageTitle() {
		return "Archive";
	}
	
}
