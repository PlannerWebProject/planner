package com.planiverse.event.repository;

import java.util.ArrayList;

import com.planiverse.event.model.EventDTO;

public interface EventDAO {

	int add(EventDTO dto);

	int change(EventDTO dto);

	int delete(String eventSeq);

	int dropchange(EventDTO dto);

	ArrayList<EventDTO> list(String id);

}
