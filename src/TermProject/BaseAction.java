package TermProject;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport implements SessionAware {

	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(User u) {
		currentUser = u;
	}
	
	public String getFoo() {
		return currentUser.getUser();
	}
	
	public boolean getLoggedIn() {
		boolean test = currentUser != null;
		return test;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		Integer uid = (Integer) session.get("uid");
		if (uid != null) {
			currentUser = User.getUserById(uid);
		} else {
			currentUser = null;
		}
	}
	
}
