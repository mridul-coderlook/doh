package com.doh.backend.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExportFolderEvent extends ApplicationEvent {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5292776918041099013L;
	
	private String jobRef;

	public ExportFolderEvent(Object source, String jobRef) {
		super(source);
		this.jobRef = jobRef;
	}
}
