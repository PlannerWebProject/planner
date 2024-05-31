package com.planiverse.event.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.planiverse.DBUtil;
import com.planiverse.event.model.EventDTO;
//EventDAO 인터페이스의 구현 클래스
public class EventDAOImpl implements EventDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	// 생성자에서 DB 연결 수행
	public EventDAOImpl() {
		this.conn = DBUtil.open();
	}
	
	/**
     * 사용자의 이벤트 목록을 조회합니다.
     * @param id 사용자 ID
     * @return 이벤트 목록을 담은 ArrayList
     */
	public ArrayList<EventDTO> list(String id) {
		try {
			String sql = "select * from vwEvent where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);  
			rs = pstat.executeQuery();

			ArrayList<EventDTO> list = new ArrayList<>();

			while (rs.next()) {
				
				EventDTO dto = new EventDTO();
				dto.setEventSeq(rs.getString("eventSeq"));
				dto.setTitle(rs.getString("title"));
				dto.setAllDay(rs.getString("allDay"));
				dto.setStart(rs.getString("start"));
				dto.setEnd(rs.getString("end"));
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
	 /**
     * 특정 캘린더의 이벤트 목록을 조회합니다.
     * @param calSeq 캘린더 시퀀스 번호
     * @return 이벤트 목록을 담은 ArrayList
     */
	public ArrayList<EventDTO> shareList(String calSeq) {
		try {
			String sql = "select * from vwEvent where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, calSeq);  
			rs = pstat.executeQuery();

			ArrayList<EventDTO> list = new ArrayList<>();

			while (rs.next()) {
				
				EventDTO dto = new EventDTO();
				dto.setEventSeq(rs.getString("eventSeq"));
				dto.setTitle(rs.getString("title"));
				dto.setAllDay(rs.getString("allDay"));
				dto.setStart(rs.getString("start"));
				dto.setEnd(rs.getString("end"));
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
	 /**
     * 이벤트 일정을 변경합니다.
     * @param dto 변경할 이벤트 정보를 담은 EventDTO 객체
     * @return 변경 성공 시 1, 실패 시 0
     */
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
	/**
     * 이벤트 정보를 수정합니다.
     * @param dto 수정할 이벤트 정보를 담은 EventDTO 객체
     * @return 수정 성공 시 1, 실패 시 0
     */
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
	  /**
     * 새로운 이벤트를 추가합니다.
     * @param dto 추가할 이벤트 정보를 담은 EventDTO 객체
     * @return 추가된 이벤트 시퀀스 번호, 실패 시 0
     */
	public int add(EventDTO dto) {
		try {
			
			String sql = "select seqEvent.nextval as eventSeq from dual";
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			int eventSeq = 0;
			if(rs.next()) {
				eventSeq = rs.getInt("eventSeq");
			}
			stat.close();
			
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
			pstat.close();
			if(result==1) {
				return eventSeq;
			}		

		} catch (Exception e) {
			System.out.println("EventDAO.add");
			e.printStackTrace();
		}
		return 0;
	}
    /**
     * 이벤트를 삭제합니다.
     * @param eventSeq 삭제할 이벤트 시퀀스 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
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
