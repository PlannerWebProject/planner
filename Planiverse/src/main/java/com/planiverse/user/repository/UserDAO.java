package com.planiverse.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.planiverse.DBUtil;
import com.planiverse.user.model.UserDTO;

public class UserDAO {
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;


	public int register(UserDTO dto) {
		try(Connection conn = DBUtil.open()) {
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
		try(Connection conn = DBUtil.open()) {
			String sql = "select count(*) as cnt from tblUser where id = ?";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			
			rs = pstat.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt");
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
		
		try(Connection conn = DBUtil.open()) {
			
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

public int socialLogin(String email, String name) {
	
	String sql = "select * from tblUser where id = ? and name = ?";
	int indicate = 0;
	try(Connection conn = DBUtil.open()) {
		pstat = conn.prepareStatement(sql);
		
		pstat.setString(1, email);
		pstat.setString(2, name);
		
		rs = pstat.executeQuery();
		
		if(rs.next()) {
			indicate = 1;
			return indicate;
		}else {
			indicate = 0;
			return indicate;
		}
		
	} catch (Exception e) {
		System.out.println("UserDAO.socialLogin");
		e.printStackTrace();
	}
	
	return indicate;
}

public int addprofile(String email, String name) {
	
	try(Connection conn = DBUtil.open()) {
		String sql = "insert into tblUser(ID, PW, NAME) values (?,?,?)";
		pstat = conn.prepareStatement(sql);
		pstat.setString(1, email);
		pstat.setString(2, "socialLogin");
		pstat.setString(3, name);
		
		return pstat.executeUpdate();
		
	} catch (Exception e) {
		System.out.println("UserDAO.register");
		e.printStackTrace();
	}
	
	return 0;
}


}
