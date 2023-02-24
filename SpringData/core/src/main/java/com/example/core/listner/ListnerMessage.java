package com.example.core.listner;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ListnerMessage extends ApplicationEvent {
	
	private String message;
	
	public ListnerMessage(Object source, String message) {
		super(source);
		this.message = message;
	}
}
