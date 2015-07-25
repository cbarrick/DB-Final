package TermProject;

import java.util.Vector;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends ActionSupport {

	//searches post table for posts with titles containing a string
	public Vector<Post> searchTitles(String s) {
		
		return Post.searchTitle(s);
		
	}
	
	//searches post table for posts within a start date s and end date e
	public Vector<Post> searchByDate(String s, String e) {
		
		return Post.getPostByDate(s, e);
		
	}
	
}
