package TermProject;

import java.sql.Date;
import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AuthenticationLoginAction extends ActionSupport implements SessionAware, ParameterAware {

	private int userID;
	private String user;
	private String password;
	private Map<String,Object>sessionMap;

	public String login() {
		Integer loggedUser = null;
		try{

			// check if the userName is already stored in the session
			if (Session.getSessionByUser(userID)!=null) {
				Session s = Session.getSessionByUser(userID);
				s.saveUserSession(s.getSessionsID());
				sessionMap.put("uid", userID);
				loggedUser = s.getUserID();
				//loggedUser = (String) sessionMap.get("user");
			}

			if (loggedUser != null && User.validateUser(loggedUser,password)) {
				return SUCCESS; // return welcome page
			}

			// if no userName stored in the session,
			// check the entered userName and password
			if (user != null && User.validateUser(userID,password)) {

				// add userName to the session
				sessionMap.put("uid", userID);
				Session s = new Session(null,null,userID);
				s.saveUserSession(null);
				return SUCCESS; // return welcome page
			}
		}catch(Exception e){
			System.out.println(e);
		}
		// in other cases, return login page
		return INPUT;

	}

	public String logout() {
		// remove userName from the session
		try{
			if (sessionMap.containsKey("uid") && Session.getSessionByUser(getUserId())!=null) {
				Session.deleteSession((int)sessionMap.get("uid"));
				sessionMap.remove("uid");
				return SUCCESS;
			}
		}catch(Exception e){
			System.err.println(e);
		}
		return ERROR;
	}
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		// TODO Auto-generated method stub
		this.sessionMap = sessionMap;

	}

	public int getUserId() {
		return userID;
	}
	
	public void setUserId(Integer ui) {
		this.userID = ui;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setParameters(Map<String, String[]> map) {
		
		user = map.get("user")[0];
		password = map.get("password")[0];
		userID = User.getUserByName(user).getId(); //**** important - this is where userid is acquired from name at login
		
		
	}
}
