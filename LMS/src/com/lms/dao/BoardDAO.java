package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public List<BoardVO> selectAllBoards() {
		String sql = "select * from tbl_board order by boardNo desc";
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setBoardNo(rs.getInt("boardNo"));
				bVo.setBoardTitle(rs.getString("boardTitle"));
				bVo.setBoardContent(rs.getString("boardContent"));
				bVo.setRegDate(rs.getDate("regDate"));
				bVo.setViewCnt(rs.getInt("viewCnt"));
				bVo.setUserNo(rs.getInt("userNo"));
				bVo.setBoardPass(rs.getString("boardPass"));
				list.add(bVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}

	public void insertBoard(BoardVO bVo) {
		String sql = "insert into tbl_board("
				+ "boardNo, boardTitle, boardContent, regDate, viewCnt, boardFile, userNo, boardPass) "
				+ "values(board_seq.nextval, ?, ?, sysdate, ?, ?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bVo.getBoardNo());
			pstmt.setString(2, bVo.getBoardTitle());
			pstmt.setString(3, bVo.getBoardContent());
			pstmt.setInt(4, bVo.getViewCnt());
			pstmt.setInt(6, bVo.getUserNo());
			pstmt.setString(7, bVo.getBoardPass());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public void updateReadCount(String boardNo) {
		String sql = "update tbl_board set viewCnt=viewCnt+1 where boardNo=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	// 게시판 글 상세 내용 보기 :글번호로 찾아온다. : 실패 null,
	public BoardVO selectOneBoardByNum(String boardNo) {
		String sql = "select * from tbl_board where boardNo = ?";
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = new BoardVO();
				bVo.setBoardNo(rs.getInt("boardNo"));
				bVo.setBoardTitle(rs.getString("boardTitle"));
				bVo.setBoardContent(rs.getString("BoardContent"));
				bVo.setRegDate(rs.getDate("regDate"));
				bVo.setViewCnt(rs.getInt("viewCnt"));
				bVo.setUserNo(rs.getInt("userNo"));
				bVo.setBoardPass(rs.getString("boardPass"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}

	public void updateBoard(BoardVO bVo) {
		String sql = "update tbl_board set boardTitle=?, boardContent=?, boardPass=?, "
				+ "  where boardNo=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getBoardTitle());
			pstmt.setString(2, bVo.getBoardContent());
			pstmt.setString(4, bVo.getBoardPass());
			pstmt.setInt(5, bVo.getBoardNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}

	public BoardVO checkPassWord(String boardPass, String boardNo) {
		String sql = "select * from tbl_board where pass=? and boardNo=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardPass);
			pstmt.setString(2, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bVo = new BoardVO();
				
				bVo.setBoardNo(rs.getInt("boardNo"));
				bVo.setBoardTitle(rs.getString("boardTitle"));
				bVo.setBoardContent(rs.getString("boardContent"));
				bVo.setRegDate(rs.getDate("regDate"));
				bVo.setViewCnt(rs.getInt("viewCnt"));
				bVo.setUserNo(rs.getInt("userNo"));
				bVo.setBoardPass(rs.getString("boardPass"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bVo;
	}

	public void deleteBoard(String boardNo) {
		String sql = "delete tbl_board where boardNo=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
