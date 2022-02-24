package com.creditsuisse.logprocessor.model;

import java.sql.Timestamp;

public class EventLog {
	private String id;
	private String state;
	private Timestamp timestamp;
	private String type;
	private String host;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public String toString() {
		return "EventLog [id=" + id + ", state=" + state + ", "
				+ "timestamp=" + timestamp + ", type=" + type + ", host="
				+ host + "]";
	}
}
