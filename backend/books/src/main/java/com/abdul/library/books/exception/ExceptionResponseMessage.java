package com.abdul.library.books.exception;

import java.time.Instant;

public class ExceptionResponseMessage {
	
	Instant time;
	int status;
	String error;
	
	public ExceptionResponseMessage(Instant time, int status, String error) {
		
		this.time = time;
		this.status = status;
		this.error = error;
	}
	
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
