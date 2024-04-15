package com.planiverse.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.util.DBUtil;

public class EventDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public EventDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<EventDTO> list() {
		try {
			String sql = "select * from tblEvent";

			stat = conn.createStatement();
			rs = stat.executeQuery(sql);

			ArrayList<EventDTO> list = new ArrayList<>();

			while (rs.next()) {
				EventDTO dto = new EventDTO();
				dto.setEventSeq(rs.getString("eventSeq"));
				dto.setTitle(rs.getString("title"));
				dto.setAllDay(rs.getString("allDay"));
				
				if (rs.getString("allDay").equals("true")) {
					dto.setStart(rs.getString("start").substring(0, 10));
					dto.setEnd(rs.getString("end").substring(0, 10));
				} else {
					dto.setStart(rs.getString("start").substring(0, 10) + "T" + rs.getString("start").substring(11));
					if (rs.getString("end") != null) {
						dto.setEnd(rs.getString("end").substring(0, 10) + "T" + rs.getString("end").substring(11));
					}
				}

				dto.setLoc(rs.getString("loc"));
				dto.setContent(rs.getString("content"));
				dto.setEDel(rs.getString("eDel"));
				dto.setGoogleCalendarId(rs.getString("googleCalendarId"));
				dto.setClassName(rs.getString("className"));
				dto.setColSeq(rs.getString("colSeq"));
				dto.setCalSeq(rs.getString("calSeq"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("EventDAO.list");
			e.printStackTrace();
		}

		return null;
	}

}
