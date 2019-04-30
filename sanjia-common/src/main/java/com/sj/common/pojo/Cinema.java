package com.sj.common.pojo;

import java.io.Serializable;

public class Cinema implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;   //影院名字	
	private String address;  //影院地址
	private String lng;  //经度
	private String lat;  //纬度
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "cinema [name=" + name + ", address=" + address + ", lng=" + lng + ", lat=" + lat + "]";
	}
	

	
}
