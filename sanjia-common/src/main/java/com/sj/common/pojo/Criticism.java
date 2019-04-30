package com.sj.common.pojo;

import java.io.Serializable;

public class Criticism implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;  //电影名称
    private String source;  //影片来源
    private String criticism;  //评论
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCriticism() {
		return criticism;
	}
	public void setCriticism(String criticism) {
		this.criticism = criticism;
	}
	@Override
	public String toString() {
		return "Criticism [name=" + name + ", source=" + source + ", criticism=" + criticism + "]";
	}
	public Criticism(String name, String source, String criticism) {
		super();
		this.name = name;
		this.source = source;
		this.criticism = criticism;
	}
	public Criticism() {


	}


    
}
