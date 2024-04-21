package com.planiverse.event.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.planiverse.DBUtil;
import com.planiverse.event.model.EventDTO;

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
				
				if (rs.getString("allDay").equals("y")) {
					dto.setStart(rs.getString("start").substring(0, 10));
					if (rs.getString("end") != null) {
					dto.setEnd(rs.getString("end").substring(0, 10));
					}
				} else {
					dto.setStart(rs.getString("start").substring(0, 10) + "T" + rs.getString("start").substring(11));
					if (rs.getString("end") != null) {
						dto.setEnd(rs.getString("end").substring(0, 10) + "T" + rs.getString("end").substring(11));
					}
				}

				dto.setLoc(rs.getString("loc"));
				dto.setContent(rs.getString("content"));
				dto.setColor(rs.getString("color"));
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
			String sql = "update tblEvent set title=?,allDay=?,\"start\"=?,\"end\"=?,loc=?,\"content\"=?, color=? where eventSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getAllDay());
			pstat.setString(3, dto.getStart());
			pstat.setString(4, dto.getEnd());
			pstat.setString(5, dto.getLoc());
			pstat.setString(6, dto.getContent());
			pstat.setString(7, dto.getColor());
			pstat.setString(8, dto.getEventSeq());
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("EventDAO.change");
			e.printStackTrace();
		}
		return 0;
	}

	public int add(EventDTO dto) {
		try {
			
			String sql = "select seqEvent.nextval as eventSeq from dual";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			int eventSeq = 0;
			if(rs.next()) {
				eventSeq = rs.getInt("eventSeq");
			}
			
			sql = "insert into tblEvent values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			pstat = conn.prepareStatement(sql);
			
			pstat.setInt(1, eventSeq);
			pstat.setString(2, dto.getTitle());
			pstat.setString(3, dto.getAllDay());
			pstat.setString(4, dto.getStart());
			pstat.setString(5, dto.getEnd());
			pstat.setString(6, dto.getLoc());
			pstat.setString(7, dto.getContent());
			pstat.setString(8, dto.getColor());
			pstat.setString(9, dto.getCalSeq());
			
			int result = pstat.executeUpdate();
			if(result==1) {
				return eventSeq;
			}		

		} catch (Exception e) {
			System.out.println("EventDAO.add");
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(String eventSeq) {
		try {
			String sql = "delete from tblEvent where eventSeq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, eventSeq);
			
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("EventDAO.delete");
			e.printStackTrace();
		}
		return 0;
	}

}
