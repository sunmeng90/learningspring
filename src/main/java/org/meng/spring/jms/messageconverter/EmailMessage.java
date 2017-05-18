package org.meng.spring.jms.messageconverter;

import java.io.Serializable;

public class EmailMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private String subject;
	private String content;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmailMessage [from=");
		builder.append(from);
		builder.append(", to=");
		builder.append(to);
		builder.append(", subject=");
		builder.append(subject);
		builder.append(", content=");
		builder.append(content);
		builder.append("]");
		return builder.toString();
	}
	
}
