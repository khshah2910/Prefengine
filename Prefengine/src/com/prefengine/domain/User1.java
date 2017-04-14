package com.prefengine.domain;


/**
 * A user POJO that represents a single user.
 * 
 * @author Yinka - UMB Preference Based Search Engine Team
 * 
 */
import java.io.Serializable;

public class User1 implements Serializable,Comparable<User1>{
	/**
	 * Serialize this class
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String email_address;
	private String hashedPassword;
	private String saltKey;
	
	/**
	 * @return the email_address
	 */
	public String getEmail_address() {
		return email_address;
	}
	/**
	 * @param id
	 * @param email_address
	 * @param hashedPassword
	 * @param saltKey
	 */
	public User1(int id, String email_address, String hashedPassword, String saltKey) {
		super();
		this.id = id;
		this.email_address = email_address;
		this.hashedPassword = hashedPassword;
		this.saltKey = saltKey;
	}
	/**
	 * @param email_address the email_address to set
	 */
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
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
	/*
	 * This compares users by their email address
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(User1 x)
	{
		return getEmail_address().compareTo(x.getEmail_address());
	}
	@Override
	public boolean equals(Object x)
	{
		if (x == null || x.getClass()!= getClass())
			return false;
		return getEmail_address().equals(((User1)x).getEmail_address());
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
