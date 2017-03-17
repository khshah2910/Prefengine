package com.prefengine.domain;


/**
 * A user POJO that represents a single user.
 * 
 * @author Yinka - UMB Preference Based Search Engine Team
 * 
 */
import java.io.Serializable;

public class User implements Serializable,Comparable<User>{
	/**
	 * Serialize this class
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String email;
	private String hashedPassword;
	private String saltKey;
	private String secQue;
	private String secAns;
	
	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email;
	}
	/**
	 * @param id
	 * @param userName
	 * @param email
	 * @param hashedPassword
	 * @param saltKey
	 * @param secQue
	 * @param secAns
	 */
	public User(int id, String userName, String email, String hashedPassword, String saltKey, 
			String secQue, String secAns) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.saltKey = saltKey;
		this.secQue = secQue;
		this.secAns = secAns;
	}
	/**
	 * @param email_address the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email = email_address;
	}
	/**
	 * @return the hashedPassword
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}
	/**
	 * @param hashedPassword the hashedPassword to set
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	/**
	 * @return the saltKey
	 */
	public String getSaltKey() {
		return saltKey;
	}
	/**
	 * @param saltKey the saltKey to set
	 */
	public void setSaltKey(String saltKey) {
		this.saltKey = saltKey;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the secQue
	 */
	public String getSecQue() {
		return secQue;
	}
	/**
	 * @param secQue the secQue to set
	 */
	public void setSecQue(String secQue) {
		this.secQue = secQue;
	}
	/**
	 * @return the secAns
	 */
	public String getSecAns() {
		return secAns;
	}
	/**
	 * @param secAns the secAns to set
	 */
	public void setSecAns(String secAns) {
		this.secAns = secAns;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/*
	 * This compares users by their email address
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(User x)
	{
		return getEmail_address().compareTo(x.getEmail_address());
	}
	@Override
	public boolean equals(Object x)
	{
		if (x == null || x.getClass()!= getClass())
			return false;
		return getEmail_address().equals(((User)x).getEmail_address());
	}
	@Override
	public int hashCode()
	{
		return getEmail_address().hashCode();
	}
	public String toString(){
		return this.getEmail_address();
	}
	
}
