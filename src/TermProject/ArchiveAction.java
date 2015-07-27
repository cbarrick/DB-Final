package TermProject;

import java.util.Map;
import java.util.List;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

public class ArchiveAction extends BaseAction implements ParameterAware {

	static int page;
	final static int PAGE_SIZE = 5;
	
	//get archive page number p. p = 0 gives the 5 most recent posts, p = 1 gives the next 5, etc.
	public List<Post> getPosts() {
		
		Post[] posts = new Post[PAGE_SIZE];
		List<Integer> postIDs = Post.getPostIDs();
		
		for(int i = 0; i < postIDs.size() && PAGE_SIZE*page + i < postIDs.size(); i++) {
			posts.add(Post.getPostByID(postIDs.elementAt(postIDs.size() - (PAGE_SIZE * page + i + 1)));
		}
		
		return posts;
		
	}
	
	public int getTotalPages() {
		
		return Math.ceiling(Post.getNumPosts()/PAGE_SIZE);
		
	}

	//only parameter to be passed is called Page_Number
	public void setParameters(Map<String, String[]> map) {
		page = Integer.parseInt(map.get("p")[0]);
	}
	
	public String getPageTitle() {
		return "Archive";
	}
	
}
