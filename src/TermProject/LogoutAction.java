package TermProject;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends BaseAction {

	private Map<String,Object> sessionMap;

	public String logout() {
		// remove userName from the session
		try{
			if (getLoggedIn()) {
				User u = getCurrentUser();
				Session.deleteSession(u.getId());
				sessionMap.remove("uid");
			}
		}catch(Exception e){
			System.err.println("Error logging out");
		}
		return SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
		super.setSession(sessionMap);
	}

}
