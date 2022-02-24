package com.creditsuisse.logprocessor.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "eventlog")
public class EventLogDTO {
	@Id
	private String id;
	private Long duration;
	private Boolean alert;
	private String type;
	private String host;
	
	public EventLogDTO() {
		super();
	}

	public EventLogDTO(String id, Long duration, Boolean alert, String type, String host) {
		super();
		this.id = id;
		this.duration = duration;
		this.alert = alert;
		this.type = type;
		this.host = host;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Boolean getAlert() {
		return alert;
	}
	public void setAlert(Boolean alert) {
		this.alert = alert;
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
		return "EventLogDTO [id=" + id + ", duration=" + duration + 
				", alert=" + alert + ", type=" + type + ", host="
				+ host + "]";
	}
}
