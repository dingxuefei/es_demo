package com.es_demo.model;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5603881893655695278L;

	
	private Long id;
	private String title;
	private String content;
	private String addTime;
	
	public Message(){super();};
	
	public Message(Long id, String title, String content, String addTime) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.addTime = addTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", content="
				+ content + ", addTime=" + addTime + "]";
	}
	
}
