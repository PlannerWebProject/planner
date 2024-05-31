package com.planiverse.user.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.planiverse.DBUtil;
import com.planiverse.user.model.UserDTO;
//사용자 관련 DAO 클래스
public class UserDAO {
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public UserDAO() {
		this.conn = DBUtil.open();
	}
	   /**
     * 새로운 사용자를 등록합니다.
     * @param dto 등록할 사용자 정보를 담은 UserDTO 객체
     * @return 등록 성공 시 1, 실패 시 0
     */
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
	/**
     * 사용자 ID 중복을 확인합니다.
     * @param id 확인할 사용자 ID
     * @return 중복된 ID 개수, 실패 시 0
     */
	public int idcheck(String id) {
		try {
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
	   /**
     * 사용자 로그인을 처리합니다.
     * @param id 로그인 ID
     * @param pw 로그인 비밀번호
     * @return 로그인 성공 여부
     */
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
/**
 * 소셜 로그인을 처리합니다.
 * @param email 소셜 로그인 이메일
 * @param name 소셜 로그인 이름
 * @return 기존 사용자인 경우 1, 신규 사용자인 경우 0
 */
public int socialLogin(String email, String name) {
	
	String sql = "select * from tblUser where id = ? and name = ?";
	int indicate = 0;
	try {
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
/**
 * 신규 사용자의 프로필 정보를 추가합니다.
 * @param email 사용자 이메일
 * @param name 사용자 이름
 * @return 추가 성공 시 1, 실패 시 0
 */
public int addprofile(String email, String name) {
	
	try {
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
