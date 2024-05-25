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
public class CalDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CalDAO() {
		this.conn = DBUtil.open();
	}

	// 새 달력리스트
	public int newCalList(String id) {
		try {
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
			pstat.setInt(2, calListSeq);
			pstat.setString(1, id);
			pstat.executeUpdate();

			return calListSeq;

		} catch (Exception e) {
			System.out.println("CalDAO.newCalList");
			e.printStackTrace();
		}

		return -1;
	}

	// 새 달력
	public int newCal(String name, int listSeq) {
		try {
			String sql = "select max(calSeq) from tblCal";
			pstat = conn.prepareStatement(sql);
			rs = pstat.executeQuery();
			int calSeq = -1;
			if (!rs.next()) {
				return calSeq;
			}
			calSeq = rs.getInt(1) + 1;
			sql = "insert into tblCal (calSeq, shareInfo, name, calListSeq) values (?, 'n', '기본', ?)";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
			pstat.setInt(2, listSeq);
			pstat.executeUpdate();

			return calSeq;

		} catch (Exception e) {
			System.out.println("CalDAO.newCal");
			e.printStackTrace();
		}

		return -1;
	}

	// 달력 리스트 조회
	public ArrayList<CalDTO> list(String id) {
		try {
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

	public int delCal(int calSeq) {
		try {
			String sql = "delete from tblShare where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
			pstat.executeUpdate();
			sql = "delete from tblEvent where calSeq = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, calSeq);
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

	public int editCal(int calSeq, String name) {
		try {
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

	public int inShare(String id, int calSeq, String token) {
		try {
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

	public ArrayList<CalDTO> shareList(String id) {
		try {
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

	public int delShareCal(String id, int calSeq) {
		try {
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

	public CalDTO getShare(String id, String token) {
		try {
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
