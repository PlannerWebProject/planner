package com.planiverse.event.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.planiverse.event.model.EventDTO;
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

	public int dropchange(EventDTO dto) {
		try {
			String sql = "update tblEvent set \"start\"=?,\"end\"=? where eventSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getStart());
			pstat.setString(2, dto.getEnd());
			pstat.setString(3, dto.getEventSeq());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("EventDAO.dropchange");
			e.printStackTrace();
		}
		return 0;
	}
	
	public int change(EventDTO dto) {
		try {
			String sql = "update tblEvent set title=?,allDay=?,\"start\"=?,\"end\"=?,loc=?,\"content\"=?, colSeq=? where eventSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getAllDay());
			pstat.setString(3, dto.getStart());
			pstat.setString(4, dto.getEnd());
			pstat.setString(5, dto.getLoc());
			pstat.setString(6, dto.getContent());
			pstat.setString(7, dto.getColSeq());
			pstat.setString(8, dto.getEventSeq());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("EventDAO.change");
			e.printStackTrace();
		}
		return 0;
	}

}
