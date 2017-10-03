package io.github.oauth.form;

import java.io.Serializable;
import org.hibernate.validator.constraints.NotBlank;

public class TermForm implements Serializable {	
	
	private static final long serialVersionUID = -157143280035400042L;
	
	@NotBlank
	private String begin;
	
	@NotBlank
	public String end;
	
	public TermForm() {
		
	}
	
	public TermForm(String begin, String end) {
		this.begin = begin;
		this.end = end;
	}
	
	public void setBegin(String begin) {
		this.begin = begin;
	}
	
	public String getBegin() {
		return begin;
	}
	
	public void setEnd(String end) {
		this.end = end;
	}
	
	public String getEnd() {
		return end;
	}
}
