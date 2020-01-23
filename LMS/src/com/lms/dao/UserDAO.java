package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lms.dto.UserVO;

import util.DBManager;

public class UserDAO {

  private UserDAO() {
  }

  private static UserDAO instance = new UserDAO();

  public static UserDAO getInstance() {
    return instance;
  }

  public int confirmID(String userno) {
	int result = -1;
    String sql = "select * from tbl_user where userNo=?";
       
    Connection connn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
      connn = DBManager.getConnection();
      pstmt = connn.prepareStatement(sql);
      pstmt.setString(1, userno);
      rs = pstmt.executeQuery();
      if (rs.next()) { 
        result = 1;
      } else { 
        result = -1;
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBManager.close(connn, pstmt, rs);
    }
    return result;
  }

  public UserVO getMember(String userno) {       
	  UserVO userVO= null;
    String sql = "select * from tbl_user where userNo=?";
     
    Connection connn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      connn = DBManager.getConnection();
      pstmt = connn.prepareStatement(sql);
      pstmt.setString(1, userno);
      rs = pstmt.executeQuery();
      if(rs.next()){
        userVO = new UserVO();
        userVO.setUserNo(rs.getInt("userNo"));
        userVO.setUserPw(rs.getString("userPw"));
        userVO.setUserName(rs.getString("userName"));
        userVO.setUserTel(rs.getInt("UserTel"));
        userVO.setState(rs.getString("state"));
        userVO.setAuthority(rs.getString("autority"));
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBManager.close(connn, pstmt, rs);
    }
    return userVO;
  }

  public int insertMember(UserVO userVO) {
    int result = 0;
    String sql = "insert into tbl_user(userNo,userPw, , userName,";
    		sql +=  "userTel, state, autority) values(?,?, ?, ?, ?, ?)";

    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
      conn = DBManager.getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, userVO.getUserNo());      
      pstmt.setString(2, userVO.getUserPw());
      pstmt.setString(3, userVO.getUserName());
      pstmt.setInt(4, userVO.getUserTel());
      pstmt.setString(5, userVO.getState());
      pstmt.setString(6, userVO.getAuthority());
      result = pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DBManager.close(conn, pstmt);
    }
    return result;
  }
  
	/* *
	 * 관리자 모드에서 사용되는 메소드 * *
	 */
	public ArrayList<UserVO> listUser(String user_name) {
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		String sql = "select * from tbl_user where name like '%'||?||'%' " +
				"order by indate desc";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			if (user_name == "") {
				pstmt.setString(1, "%");
			} else {
				pstmt.setString(1, user_name);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				UserVO userVO = new UserVO();
				userVO.setUserNo(rs.getInt("userNo"));
				userVO.setUserPw(rs.getString("userPw"));
				userVO.setUserName(rs.getString("userName"));
				userVO.setUserTel(rs.getInt("userTel"));
				userVO.setState(rs.getString("state"));
				userVO.setAuthority(rs.getString("authority"));
		        userList.add(userVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return userList;
	}
//중복체크
	public int userCheck(String userno, String userpw) {

		int result = -1;
		String sql ="select userpw from tbl_user where userno=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
				if(rs.getString("userpw")!= null && rs.getString("userpw").equals(userpw)){
					result = 1;
				} else {
					
					result = 0;
				} 
			}else {
				result = -1;
			}
		}catch (Exception e) {

		e.printStackTrace();
			
		}finally {
			
			try {
				
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
						
			}catch(Exception e) {
				e.printStackTrace();
			}
		}return result;
		
		
		
		
	}

	public Connection getConnection() throws Exception {
		Connection conn =null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		
		
		return conn;
	}
}
