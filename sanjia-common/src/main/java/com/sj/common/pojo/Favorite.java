package com.sj.common.pojo;

import java.io.Serializable;

public class Favorite implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String favoriteId; //购票信息唯一标识
	
	
	public Favorite(String useName, String favoriteId) {
		super();
		this.userName = userName;
		this.favoriteId = favoriteId;
	}
	
	public Favorite() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(String favoriteId) {
		this.favoriteId = favoriteId;
	}
    
    
}
