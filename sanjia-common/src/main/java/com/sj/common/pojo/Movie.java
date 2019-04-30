package com.sj.common.pojo;

import java.io.Serializable;

public class Movie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;  //电影名称
	private String category;  //类别
	private String show_time;  //上映时间
	private String duration;  //电影时长
	private String introdution;  //简介
	private String actors;  //演员列表
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getIntrodution() {
		return introdution;
	}
	public void setIntrodution(String introdution) {
		this.introdution = introdution;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public Movie(String name, String category, String show_time, String duration, String introdution, String actors) {
		super();
		this.name = name;
		this.category = category;
		this.show_time = show_time;
		this.duration = duration;
		this.introdution = introdution;
		this.actors = actors;
	}
	public Movie() {
		
	}
	@Override
	public String toString() {
		return "Movie [name=" + name + ", category=" + category + ", show_time=" + show_time + ", duration=" + duration
				+ ", introdution=" + introdution + ", actors=" + actors + "]";
	}
	
	

}
