package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.lms.dto.UserVO;

public class UserDAO {
	private UserDAO() {
	}

	private static UserDAO instance = new UserDAO();

	public static UserDAO getInstance() {
		return instance;
	}

	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context) initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		return conn;
	}

	// 사용자 인증시 사용하는 메소드
	public int userCheck(String userno, String userpw) {
		int result = -1;
		String sql = "select USER_PW from tbl_user where USER_NO=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("user_pw") != null
						&& rs.getString("user_pw").equals(userpw)) {
					result = 1;
				} else {
					result = 0;
				}
			} else {
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 아이디로 회원 정보 가져오는 메소드
	public UserVO getUser(String userno) {
		UserVO mVo = null;
		String sql = "select * from TBL_USER where USER_NO=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mVo = new UserVO();
				mVo.setUsername(rs.getString("user_name"));
				mVo.setUserno(rs.getString("user_no"));
				mVo.setUserpw(rs.getString("user_pw"));
				mVo.setUsertel(rs.getInt("user_tel"));
				mVo.setState(rs.getString("state"));
				mVo.setAuthority(rs.getString("authority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mVo;
	}

	public int confirmID(String userno) {
		int result = -1;
		String sql = "select USER_NO from TBL_USER where USER_NO=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
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
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int insertUser(UserVO mVo) {
		int result = -1;
		String sql = "insert into TBL_USER values(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getUsername());
			pstmt.setString(2, mVo.getUserno());
			pstmt.setString(3, mVo.getUserpw());
			pstmt.setInt(4, mVo.getUsertel());
			pstmt.setString(5, mVo.getState());
			pstmt.setString(6, mVo.getAuthority());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateUser(UserVO mVo) {
		int result = -1;
		String sql = "update TBL_USER set USER_PW=?, USER_TEL=?,"
				+ "STATE=?, AUTHORITY=? where USER_NO=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getUserpw());
			pstmt.setInt(2, mVo.getUsertel());
			pstmt.setString(3, mVo.getState());
			pstmt.setString(4, mVo.getAuthority());
			pstmt.setString(5, mVo.getUserno());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
