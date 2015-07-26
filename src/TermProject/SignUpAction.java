package com.Dbms.Struts2.Demo;


import java.util.Map;



import com.opensymphony.xwork2.ActionSupport;

public class SignUpAction extends ActionSupport {

	private Integer Id;
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public java.sql.Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(java.sql.Date signupDate) {
		this.signupDate = signupDate;
	}

	private String email;
	private String password;
	private String Role;
	private java.sql.Date signupDate;

	public String signUp() {

		try{

			User newUser = new User(null,getEmail(),getPassword(),getRole(),null);
			newUser.saveUser();

		}catch(Exception e){
			System.out.println(e +" could not create new user");
		}
		// in other cases, return login page
		return SUCCESS;
	}

}