package TermProject;

import java.util.Map;
import java.util.List;
import java.util.Vector;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends BaseAction implements ParameterAware {

	String search, start, end;
	
	//searches post table for posts with titles containing a string
	public List<Post> searchTitles() {
		
		return Post.searchTitle(search);
		
	}
	
	//searches post table for posts within a start date s and end date e
	public Vector<Post> searchByDate() {
		
		return Post.getPostByDate(start, end);
		
	}

	//three parameters, Search_String, Start_Date, End_Date
	public void setParameters(Map<String, String[]> map) {
		search = map.get("Search_String")[0];
		start = map.get("Start_Date")[0];
		end = map.get("End_Date")[0];
	}
	
	public String getPageTitle() {
		return "Search";
	}
	
}
