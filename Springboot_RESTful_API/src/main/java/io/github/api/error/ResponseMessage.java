package io.github.api.error;

// Response error message
public class ResponseMessage {
	
	private String message;
	
	private String type;
	
	public ResponseMessage() {
	}
	
	public ResponseMessage(String message, String type) {
		this.message = message;
		this.type = type;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
