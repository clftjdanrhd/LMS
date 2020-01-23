package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lms.dto.FileVO;

import util.DBManager;

public class FileDAO {
	
	private static FileDAO instance = new FileDAO();

	public static FileDAO getInstance() {
		return instance;
	}


	public List<FileVO> getShopImageList(String fileNo) {

		List<FileVO> fileList = new ArrayList<FileVO>();

		Connection conn = DBManager.getConnection();

		ResultSet rs = null; 
		PreparedStatement st = null;

		String sql = "SELECT FILE_NO, FILE_NAME, BOARD_NO FROM TBL_FILE WHERE FILE_NO = ? ORDER BY FILE_NAME";

		try {

			st = conn.prepareStatement(sql);

			st.setString(1, menuNo);
			rs = st.executeQuery(); 

			while (rs.next()) {

				FileVO iVo = new FileVO();
				iVo.setImgNo(rs.getInt("IMAGE_NO"));
				iVo.setImgPath(rs.getString("IMAGE_PATH"));
				iVo.setImgOrder(rs.getInt("IMAGE_ORDER"));

				imgList.add(iVo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, st, rs);
		}

		return imgList;
	}
	


	public FileVO getMenuFristImage(int menuNo) {

		FileVO iVo = new FileVO();

		Connection conn = DBManager.getConnection();

		ResultSet rs = null; 
		PreparedStatement st = null;

		String sql = "SELECT IMAGE_NO, IMAGE_PATH FROM TB_IMAGE WHERE MENU_NO = ? AND IMAGE_ORDER=1";

		try {

			st = conn.prepareStatement(sql);

			st.setInt(1, menuNo);
			rs = st.executeQuery(); 

			while (rs.next()) {

				iVo.setImgNo(rs.getInt("IMAGE_NO"));
				iVo.setImgPath(rs.getString("IMAGE_PATH"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, st, rs);
		}

		return iVo;
	}


	public int createMenuImage(FileVO iVo, int menuNo) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "INSERT INTO TB_IMAGE ( IMAGE_NO, IMAGE_PATH, IMAGE_ORDER, MENU_NO )" + "  VALUES(IMAGE_SEQ.nextval, ?, ?, ?)";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, iVo.getImgPath());
			st.setInt(2, iVo.getImgOrder());
			st.setInt(3, menuNo);

			res = st.executeUpdate(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = 0;
			e.printStackTrace();

		} finally {
			DBManager.close(conn, st);
		}

		return res;
	}

	public int deleteImageName(String imgName) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "DELETE FROM TB_IMAGE WHERE IMAGE_NAME = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, imgName);

			res = st.executeUpdate(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = 0;
			e.printStackTrace();

		} finally {
			DBManager.close(conn, st);
		}

		return res;

	}
	
	

	public int deleteMenuImage(int no) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "DELETE FROM TB_IMAGE WHERE MENU_NO = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, no);

			res = st.executeUpdate(); 

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			res = 0;
			e.printStackTrace();

		} finally {
			DBManager.close(conn, st);
		}

		return res;

	}

}
