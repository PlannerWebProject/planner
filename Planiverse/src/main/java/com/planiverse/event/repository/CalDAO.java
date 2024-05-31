package com.planiverse.event.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.planiverse.DBUtil;
import com.planiverse.event.model.CalDTO;
import com.planiverse.event.model.EventDTO;

//CalDAO.java
//캘린더 관련 DAO 클래스
public class CalDAO {

	//private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	// 새 달력리스트
	  /**
     * 새로운 캘린더 목록을 생성합니다.
     * @param id 사용자 ID
     * @return 생성된 캘린더 목록 시퀀스 번호, 실패 시 -1
     */
	public int newCalList(String id) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "select max(calListSeq) from tblCalList";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			int calListSeq = -1;
			if (!rs.next()) {
				return calListSeq;
			}
			calListSeq = rs.getInt(1) + 1;

			sql = "insert into tblCalList (calListSeq, id) values (?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calListSeq);
			pstat.setString(2, id);
			pstat.executeUpdate();

			return calListSeq;

		} catch (Exception e) {
			System.out.println("CalDAO.newCalList");
			e.printStackTrace();
		}

		return -1;
	}

	// 새 달력
	 /**
     * 새로운 캘린더를 생성합니다.
     * @param name 캘린더 이름
     * @param listSeq 캘린더 목록 시퀀스 번호
     * @return 생성된 캘린더 시퀀스 번호, 실패 시 -1
     */
	public int newCal(String name, int listSeq) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "select max(calSeq) from tblCal";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			int calSeq = -1;
			if (!rs.next()) {
				return calSeq;
			}
			calSeq = rs.getInt(1) + 1;
			sql = "insert into tblCal (calSeq, shareInfo, name, calListSeq) values (?, 'n', ?, ?)";
	        pstat = conn.prepareStatement(sql);
	        
	        pstat.setInt(1, calSeq);
	        pstat.setString(2, name);
	        pstat.setInt(3, listSeq);
	        pstat.executeUpdate();

			return calSeq;

		} catch (Exception e) {
			System.out.println("CalDAO.newCal");
			e.printStackTrace();
		}

		return -1;
	}

	// 달력 리스트 조회
	/**
     * 사용자의 캘린더 목록과 공유받은 캘린더 목록을 조회합니다.
     * @param id 사용자 ID
     * @return 캘린더 목록을 담은 ArrayList
     */
	public ArrayList<CalDTO> list(String id) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "select * from tblCal a " + "inner join tblCalList b on a.calListSeq = b.calListSeq "
					+ "left join tblShare s on a.calseq = s.calseq where b.id = ? and s.id = ?" + "order by a.calSeq";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, id);
			rs = pstat.executeQuery();

			ArrayList<CalDTO> list = new ArrayList<>();

			while (rs.next()) {
				CalDTO dto = new CalDTO();
				dto.setCalSeq(rs.getString("calSeq"));
				dto.setName(rs.getString("name"));
				dto.setShareInfo(rs.getString("shareInfo"));
				dto.setCalListSeq(rs.getString("calListSeq"));
				dto.setToken(rs.getString("shareTK"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("CalDAO.list");
			e.printStackTrace();
		}

		return null;
	}
	 /**
     * 캘린더를 삭제합니다.
     * @param calSeq 삭제할 캘린더 시퀀스 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
	public int delCal(int calSeq) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "delete from tblShare where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
			pstat.executeUpdate();
			pstat.close();
			sql = "delete from tblEvent where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
			pstat.executeUpdate();
			pstat.close();
			sql = "delete from tblCal where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CalDAO.delCal");
			e.printStackTrace();
		}

		return 0;
	}
	 /**
     * 캘린더 정보를 수정합니다.
     * @param calSeq 수정할 캘린더 시퀀스 번호
     * @param name 수정할 캘린더 이름
     * @return 수정 성공 시 1, 실패 시 0
     */
	public int editCal(int calSeq, String name) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "update tblCal set name = ? where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, name);
			pstat.setInt(2, calSeq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CalDAO.editCal");
			e.printStackTrace();
		}

		return 0;
	}
	  /**
     * 캘린더 공유 정보를 추가합니다.
     * @param id 공유 받는 사용자 ID
     * @param calSeq 공유할 캘린더 시퀀스 번호
     * @param token 공유 토큰
     * @return 추가 성공 시 1, 실패 시 0
     */
	public int inShare(String id, int calSeq, String token) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "insert into tblShare (id, calSeq, shareTK) values (?, ?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setInt(2, calSeq);
			pstat.setString(3, token);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CalDAO.inShareCal");
			e.printStackTrace();
		}

		return 0;
	}
	   /**
     * 공유받은 캘린더 목록을 조회합니다.
     * @param id 사용자 ID
     * @return 공유받은 캘린더 목록을 담은 ArrayList
     */
	public ArrayList<CalDTO> shareList(String id) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "select * from tblCal a " + "inner join tblCalList b on a.calListSeq = b.calListSeq "
					+ "where a.calseq in (select calseq from tblshare where id = ?) and b.id != ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, id);
			rs = pstat.executeQuery();

			ArrayList<CalDTO> list = new ArrayList<>();

			while (rs.next()) {
				CalDTO dto = new CalDTO();
				dto.setCalSeq(rs.getString("calSeq"));
				dto.setName(rs.getString("name"));
				dto.setShareInfo(rs.getString("shareInfo"));
				dto.setCalListSeq(rs.getString("calListSeq"));

				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("CalDAO.shareList");
			e.printStackTrace();
		}

		return null;
	}
	 /**
     * 공유 캘린더를 삭제합니다.
     * @param id 사용자 ID
     * @param calSeq 삭제할 캘린더 시퀀스 번호
     * @return 삭제 성공 시 1, 실패 시 0
     */
	public int delShareCal(String id, int calSeq) {
		try(
				Connection conn = DBUtil.open();
		) {
			String sql = "delete from tblShare where id = ? and calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setInt(2, calSeq);
			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("CalDAO.delShareCal");
			e.printStackTrace();
		}

		return 0;
	}
	  /**
     * 공유 캘린더 정보를 가져옵니다.
     * @param id 사용자 ID
     * @param token 공유 토큰
     * @return 공유 캘린더 정보를 담은 CalDTO 객체, 해당 캘린더가 없는 경우 null
     */
	public CalDTO getShare(String id, String token) {
		try(
				Connection conn = DBUtil.open();
		) {
			// Get the calSeq based on the token
			String sql = "select min(calseq) as calseq from tblshare where sharetk = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, token);
			rs = pstat.executeQuery();
			String calSeq = null; // Changed from empty string to null
			if (rs.next()) {
				calSeq = rs.getString("calseq");
			}

			// If calSeq is not found, return null
			if (calSeq == null) {
				return null;
			}

			// Insert into tblShare
			sql = "insert into tblShare (id, calseq, sharetk) values (?, ?, ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, calSeq);
			pstat.setString(3, token);
			pstat.executeUpdate();
			pstat.close();

			// Retrieve the cal data
			sql = "select * from tblcal where calseq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, calSeq);
			rs = pstat.executeQuery();

			CalDTO dto = null;
			if (rs.next()) {
				dto = new CalDTO();
				dto.setCalSeq(rs.getString("calSeq"));
				dto.setCalListSeq(rs.getString("CalListSeq"));
				dto.setName(rs.getString("name"));
			}

			return dto;

		} catch (Exception e) {
			System.out.println("CalDAO.getShare");
			e.printStackTrace();
		}

		return null;
	}

}
