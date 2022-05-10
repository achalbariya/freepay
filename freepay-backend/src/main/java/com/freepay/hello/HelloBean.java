package com.freepay.hello;

public class HelloBean {

	private String message;

	public HelloBean(String message) {
		this.setMessage(message);
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "HelloBean [Message=" + message + "]";
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
