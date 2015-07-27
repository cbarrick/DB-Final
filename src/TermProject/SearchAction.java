package TermProject;

import java.util.Map;
import java.util.List;

import org.apache.struts2.interceptor.ParameterAware;
import com.opensymphony.xwork2.ActionSupport;

public class SearchAction extends BaseAction implements ParameterAware {

	String search;
	List<Post> results;
	
	//searches post table for posts with titles containing a string
	public String searchTitles() {
		results = Post.searchTitle(search);
		return "success";
	}
	
	public List<Post> getPosts() {
		return results;
	}

	//three parameters, Search_String, Start_Date, End_Date
	public void setParameters(Map<String, String[]> map) {
		search = map.get("q")[0];
	}
	
	public String getPageTitle() {
		return "Search";
	}
	
}
