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


	public ArrayList<FileVO> getFileList(String boardNo) {

		ArrayList<FileVO> fileList = new ArrayList<FileVO>();

		Connection conn = DBManager.getConnection();

		ResultSet rs = null; 
		PreparedStatement st = null;

		String sql = "SELECT FILE_NO, FILE_NAME, BOARD_NO FROM TBL_FILE WHERE BOARD_NO = ?";

		try {

			st = conn.prepareStatement(sql);

			st.setString(1, boardNo);
			rs = st.executeQuery(); 

			while (rs.next()) {

				FileVO fVo = new FileVO();
				fVo.setFileNo(rs.getInt("FILE_NO"));
				fVo.setFileName(rs.getString("FILE_NAME"));
				fVo.setBoardNo(rs.getInt("BOARD_NO"));

				fileList.add(fVo);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, st, rs);
		}

		return fileList;
	}
	



	public int createFile(FileVO fVo, int boardNo) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "INSERT INTO TBL_FILE ( FILE_NO, FILE_NAME, BOARD_NO )" + "  VALUES(FILE_SEQ.nextval, ?, ?)";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, fVo.getFileName());
			st.setInt(2, boardNo);

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

	public int deleteFileName(String fileName) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "DELETE FROM TBL_FILE WHERE FILE_NAME = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, fileName);

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
	
	

	public int deleteFile(String boardNo) {

		int res = 0;

		Connection conn = DBManager.getConnection();

		PreparedStatement st = null;

		String sql = "DELETE FROM TBL_FILE WHERE BOARD_NO = ?";

		try {
			st = conn.prepareStatement(sql);
			st.setString(1, boardNo);

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
