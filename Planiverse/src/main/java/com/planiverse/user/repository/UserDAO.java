package com.planiverse.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.planiverse.DBUtil;
import com.planiverse.user.model.UserDTO;

public class UserDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public UserDAO() {
		this.conn = DBUtil.open();
	}

	public int register(UserDTO dto) {
		try {
			String sql = "insert into tblUser values (?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			pstat.setString(3, dto.getName());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("UserDAO.register");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int idcheck(String id) {
		try {
			String sql = "select count(*) as cnt from tblUser where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			if(rs.next()) {
				if(rs.getInt("cnt")==0) return 1;
			}
			
		} catch (Exception e) {
			System.out.println("UserDAO.register");
			e.printStackTrace();
		}
		
		return 0;
	}
	
public boolean login(String id, String pw) {
		
		String sql ="select * from tblUser where id = ? and  pw = ?";
		boolean isSuccess = false;
		
		try {
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, id);
			pstat.setString(2, pw);
			
			ResultSet rs = pstat.executeQuery();
			
	        if (rs.next()) {
	           
	            isSuccess = true;
	        } else {
	        
	            isSuccess = false;
	        }
	        rs.close();

	    } catch (SQLException e) {
	     
	        e.printStackTrace();
	    } 

	    return isSuccess;
			
		
		
	}
}
