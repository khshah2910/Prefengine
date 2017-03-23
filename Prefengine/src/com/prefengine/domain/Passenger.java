package com.prefengine.domain;

public class Passenger {
	private int adults = 0;
	private int children = 0;
	private int seniors = 0;
	/**
	 * @param adults
	 * @param children
	 * @param senior
	 */
	public Passenger(int adults, int children, int seniors) {
		super();
		this.adults = adults;
		this.children = children;
		this.seniors = seniors;
	}
	/**
	 * @return the adult count
	 */
	public int getAdults() {
		return adults;
	}
	/**
	 * @param adults the number of adults to set
	 */
	public void setAdults(int adults) {
		this.adults = adults;
	}
	/**
	 * @return the children count
	 */
	public int getChildren() {
		return children;
	}
	/**
	 * @param children the number of children to set
	 */
	public void setChildren(int children) {
		this.children = children;
	}
	/**
	 * @return the senior count
	 */
	public int getSeniors() {
		return seniors;
	}
	/**
	 * @param senior the number of seniors to set
	 */
	public void setSeniors(int seniors) {
		this.seniors = seniors;
	}
	
	
	
}
