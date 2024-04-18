package com.planiverse.event.model;

import lombok.Data;

@Data
public class EventDTO {
	private String eventSeq;
	private String title;
	private String allDay;
	private String start;
	private String end;
	private String loc;
	private String content;
	private String color;
	private String calSeq;
}
