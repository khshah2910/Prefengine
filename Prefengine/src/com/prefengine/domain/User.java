package com.prefengine.domain;

public class User {
	private int id;
	private String userName;
	private String email;
	private String password;
	private String secQue;
	private String secAns;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getSecQue() {
		return secQue;
	}
	public void setSecQue(String secQue) {
		this.secQue = secQue;
	}
	public String getSecAns() {
		return secAns;
	}
	public void setSecAns(String secAns) {
		this.secAns = secAns;
	}

}
