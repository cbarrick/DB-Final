package TermProject;

import java.util.Map;
import java.util.List;

import org.apache.struts2.interceptor.ParameterAware;

import com.opensymphony.xwork2.ActionSupport;

public class ArchiveAction extends BaseAction implements ParameterAware {

	static int page;
	
	//get archive page number p. p = 0 gives the 5 most recent posts, p = 1 gives the next 5, etc.
	public List<Post> archivePage() {
		
		Post[] posts = new Post[5];
		List<Integer> postIDs = Post.getPostIDs();
		
		for(int i = 0; i < postIDs.size() && 5*page + i < postIDs.size(); i++) {
			posts.add(Post.getPostByID(postIDs.elementAt(postIDs.size() - (5 * page + i + 1)));
		}
		
		return posts;
		
	}
	
	public int getTotalPages() {
		
		return Post.getNumPosts();
		
	}

	//only parameter to be passed is called Page_Number
	public void setParameters(Map<String, String[]> map) {
		page = Integer.parseInt(map.get("Page_Number")[0]);
	}
	
	public String getPageTitle() {
		return "Archive";
	}
	
}
