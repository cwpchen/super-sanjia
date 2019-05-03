package com.sj.common.pojo;

import java.io.Serializable;

public class Movie implements Serializable{
	/**
	 * 
		Field			Type		Comment
		id				bigint(11) NOT NULL
		name			varchar(96) NOT NULL
		status			varchar(20) NULL
		catagory		varchar(60) NULL
		show_time		varchar(240) NULL
		duration		varchar(90) NULL
		actors			varchar(600) NULL
		introduction	varchar(3000) NULL
		score			varchar(60) NULL
		location		varchar(600) NULL
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;  //电影名称
	private String status;
	private String catagory;  //类别
	private String showTime;  //上映时间
	private String duration;  //电影时长
	private String actors;  //演员列表
	private String introduction;  //简介
	private String score;
	private String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Movie(int id,String name, String catagory,String status,
				String showTime, String duration, String introduction, String actors,
				String score, String location) {
		super();
		this.id=id;
		this.name = name;
		this.status = status;
		this.catagory = catagory;
		this.showTime = showTime;
		this.duration = duration;
		this.setIntroduction(introduction);
		this.actors = actors;
		this.score= score;
		this.location = location;
	}
	public Movie() {
		
	}
	@Override
	public String toString() {
		return "Movie ["
				+ "id="+id+", "
				+ "name=" + name + ", "
				+ "status=" + status + ", "
				+ "category=" + catagory+ ", "
				+ "score="+score+", "
				+"showTime=" + showTime + ", "
				+"duration=" + duration+ ","
				+"introduction=" + introduction + ", "
				+"actors=" + actors +","
				+ "location="+location
				+ "]";
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	

}
