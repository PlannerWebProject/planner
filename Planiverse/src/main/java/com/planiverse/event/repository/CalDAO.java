package com.planiverse.event.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.planiverse.DBUtil;
import com.planiverse.event.model.CalDTO;
import com.planiverse.event.model.EventDTO;

public class CalDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public CalDAO() {
		this.conn = DBUtil.open();
	}
	
	//새 달력리스트
	public String newCalList(String id) {
		
		try {
			String sql = "insert into tblCalList (calListSeq, id) values (nvl((select max(calListSeq) from tblCalList)+1,1), ?)";
			pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pstat.setString(1, id);
			pstat.executeUpdate();
			rs = pstat.getGeneratedKeys();

			if (rs.next()) {
                String generatedCalListSeq = rs.getString(1);
                return generatedCalListSeq;
            }

		} catch (Exception e) {
			System.out.println("CalDAO.newCalList");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//새 달력
	public String newCal(String id, String name, String listSeq) {
		
		try {
			String sql = "insert into tblCal (calSeq, shareInfo, name, calListSeq) values (nvl((select max(calSeq) from tblCal)+1,1), 'n', ?, ?)";
			pstat = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        pstat.setString(1, id);
	        pstat.setString(2, name);
	        pstat.setString(3, listSeq);
			pstat.executeUpdate();
			rs = pstat.getGeneratedKeys();

			if (rs.next()) {
                String generatedCalListSeq = rs.getString(1);
                return generatedCalListSeq;
            }

		} catch (Exception e) {
			System.out.println("CalDAO.newCalList");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//달력 리스트 조회
	public ArrayList<CalDTO> list(String id) {
		
		try {
			String sql = "select * from tblCal a inner join tblCalList b on a.calListSeq = b.calListSeq where b.id = = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			rs = pstat.executeQuery();

			ArrayList<CalDTO> list = new ArrayList<>();

			while (rs.next()) {
				
				CalDTO dto = new CalDTO();
				dto.setCalSeq(rs.getString("calSeq"));
				dto.setName(rs.getString("name"));;
				dto.setShareInfo(rs.getString("shareInfo"));;
				dto.setCalListSeq(rs.getString("calListSeq"));;
				
				list.add(dto);
			}
			return list;

		} catch (Exception e) {
			System.out.println("CalDAO.list");
			e.printStackTrace();
		}
		
		return null;
	}
	
}








