package com.simi.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_table")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable = false)
	private long ID;
	
	@Column(name ="message", length=5000)
	private String message;
	
	@Column(name="messageTo")
	private long messageTo;
	
	@Column(name="messageBy")
	private long messageBy;
	
	@Column(name="messageByName")
	private String messageByName;
	
	@Column(name="messageSentOn")
	private String messageSentOn;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getMessageTo() {
		return messageTo;
	}

	public void setMessageTo(long messageTo) {
		this.messageTo = messageTo;
	}

	public String getMessageSentOn() {
		return messageSentOn;
	}

	public void setMessageSentOn(String messageSentOn) {
		this.messageSentOn = messageSentOn;
	}

	public long getMessageBy() {
		return messageBy;
	}

	public void setMessageBy(long messageBy) {
		this.messageBy = messageBy;
	}

	public String getMessageByName() {
		return messageByName;
	}

	public void setMessageByName(String messageByName) {
		this.messageByName = messageByName;
	}

	
	
	
}
