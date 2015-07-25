package TermProject;

import java.util.Vector;
import com.opensymphony.xwork2.ActionSupport;

public class ArchiveAction extends ActionSupport {

	//get archive page number p. p = 0 gives the 5 most recent posts, p = 1 gives the next 5, etc.
	public Post[] archivePage(int p) {
		
		Post[] posts = new Post[5];
		Vector<Integer> postIDs = Post.getPostIDs();
		
		for(int i = 0; i < postIDs.size() && 5*p + i < postIDs.size(); i++) {
			posts[i] = Post.getPostByID(postIDs.elementAt(postIDs.size() - (5 * p + i + 1)));
		}
		
		return posts;
		
	}
	
}
