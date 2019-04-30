package com.sj.common.pojo;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;  
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String pass;
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", pass=" + pass + "]";
	}
	
	
}
