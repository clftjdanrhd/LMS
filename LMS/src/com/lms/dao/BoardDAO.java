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
import util.SearchCriteria;

public class BoardDAO {
	private BoardDAO() {
	}

	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	public int listSearchCount(SearchCriteria cri) {

	      String sql = "SELECT COUNT(*) AS CNT FROM"
	    		  	 +" (SELECT ROWNUM AS RNUM, B.BOARD_NO, B.BOARD_TITLE, B.REGDATE, B.VIEWCNT, U.USER_NAME"
	    		  	 +" FROM TBL_BOARD B, TBL_USER U"
	    			 +" WHERE B.USER_NO = U.USER_NO)";

	     // String searchTypeQuery = " AND B.BOARD_TITLE = " + cri.getListType();

		/*
		 * if (!cri.getListType().equals("")) {
		 * 
		 * sql += searchTypeQuery;
		 * 
		 * } else { sql += " ) "; }
		 */
	      System.out.println(sql);

	      int cnt = 0;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      try {
	         conn = DBManager.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs = pstmt.executeQuery();

	         if (rs.next()) {

	            cnt = rs.getInt("CNT");

	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DBManager.close(conn, pstmt, rs);
	      }
	      return cnt;
	   }

	   public ArrayList<BoardVO> selectAllBoards(SearchCriteria cri) {

	      String sql = " SELECT * FROM("
	    		     +" SELECT ROWNUM AS RNUM, BOARD_NO, BOARD_TITLE,REGDATE, VIEWCNT,USER_NAME"
	    		     +" FROM (SELECT B.BOARD_NO, B.BOARD_TITLE, B.REGDATE, B.VIEWCNT, U.USER_NAME"
	    		     +" FROM TBL_BOARD B, TBL_USER U WHERE B.USER_NO = U.USER_NO"
	    		     +" ORDER BY B.BOARD_NO DESC))";
                  

	      //String searchTypeQuery = " AND B.BOARD_TITLE = " + cri.getListType();
	      String pagingQuery = " WHERE rnum >= " + cri.getPageStart() + " AND rnum <= " + (cri.getPageStart()+cri.getPerPageNum()-1);
	    		  
	      
	      //boardno !

		/*
		 * if (!cri.getListType().equals("")) {
		 * 
		 * sql += searchTypeQuery;
		 * 
		 * } else { sql += " ) "; }
		 */

	      sql += pagingQuery;

	      System.out.println(sql);

	      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;
	      try {
	         conn = DBManager.getConnection();
	         stmt = conn.createStatement();
	         rs = stmt.executeQuery(sql);
	         while (rs.next()) {
	        	 BoardVO bVo = new BoardVO();
	        	 bVo.setBoardNo(rs.getInt("board_no"));
	        	 bVo.setBoardTitle(rs.getString("board_title"));
	        	 bVo.setRegDate(rs.getDate("regdate"));
	        	 bVo.setViewCnt(rs.getInt("viewcnt"));
	        	 bVo.setUserName(rs.getString("user_name"));

	            list.add(bVo);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DBManager.close(conn, stmt, rs);
	      }
	      return list;
	   }
	
	  public List<BoardVO> catchBoardNo(String boardNo) {
	  
	  String sql = "select board_no from tbl_board where board_no = ?";
	  
	  
	  List<BoardVO> list = new ArrayList<BoardVO>(); 
	  Connection conn = null;
	  PreparedStatement pstmt = null; 
	  ResultSet rs = null;
	  BoardVO bVo =  null;
	  
	  try { 
		  conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bVo = new BoardVO();
				bVo.setBoardNo(rs.getInt("board_no"));
				list.add(bVo); 
				
				System.out.println("boardDao : " + list);
	  } 
	  } catch (SQLException e) {
		  e.printStackTrace(); 
		  } finally { 
			  DBManager.close(conn,pstmt, rs); 
			  
			  } return list; 
			  
	  }
	 

	public int insertBoard(BoardVO bVo) {
		String sql = "insert into tbl_board("
				+ "board_no, board_title, board_content, regdate, user_no, board_pass) "
				+ "values(board_seq.nextval, ?, ?, sysdate, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rsKey = null;
		int boardNo=0;
		
		String generatedColumns[] = { "board_no" };
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql, generatedColumns);
			pstmt.setString(1, bVo.getBoardTitle());
			pstmt.setString(2, bVo.getBoardContent());
			pstmt.setString(3, bVo.getUserNo());
			pstmt.setString(4, bVo.getBoardPass());
			pstmt.executeUpdate();
			
			rsKey = pstmt.getGeneratedKeys();

			if (rsKey.next())
				boardNo = rsKey.getInt(1);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return boardNo;
	}

	public void updateReadCount(String boardNo) {
		String sql = "update tbl_board set viewcnt=viewcnt+1 where board_no=?";
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

	// �Խ��� �� �� ���� ���� :�۹�ȣ�� ã�ƿ´�. : ���� null,
	public BoardVO selectOneBoardByNum(String boardNo) {
		
		String sql = "select b.board_no, b.board_title, b.board_content, b.regdate, b.viewcnt, u.user_name, u.user_no, b.board_pass" 
				   + " from tbl_board b, tbl_user u"  
				   + " where b.user_no = u.user_no"
				   + " and board_no = ?";
		
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
				bVo.setBoardNo(rs.getInt("board_no"));
				bVo.setBoardTitle(rs.getString("board_title"));
				bVo.setBoardContent(rs.getString("board_content"));
				bVo.setRegDate(rs.getDate("regdate"));
				bVo.setViewCnt(rs.getInt("viewcnt"));
				bVo.setUserName(rs.getString("user_name"));
				bVo.setBoardPass(rs.getString("board_pass"));
				bVo.setUserNo(rs.getString("user_no"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bVo;
	}

	public void updateBoard(BoardVO bVo) {
	      String sql = "update tbl_board set board_title=?, board_content=?, board_pass=? "
	            + "  where board_no=?";
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      try {
	         conn = DBManager.getConnection();
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, bVo.getBoardTitle());
	         pstmt.setString(2, bVo.getBoardContent());
	         pstmt.setString(3, bVo.getBoardPass());
	         pstmt.setInt(4, bVo.getBoardNo());
	         pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DBManager.close(conn, pstmt);
	      }
	   }


	public BoardVO checkPassWord(String boardPass, String boardNo) {
		String sql = "select * from tbl_board where board_pass=? and board_no=?";
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
				
				bVo.setBoardNo(rs.getInt("board_no"));
				bVo.setBoardTitle(rs.getString("board_title"));
				bVo.setBoardContent(rs.getString("board_content"));
				bVo.setRegDate(rs.getDate("regdate"));
				bVo.setViewCnt(rs.getInt("viewcnt"));
				bVo.setUserNo(rs.getString("user_no"));
				bVo.setBoardPass(rs.getString("board_pass"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bVo;
	}

	public void deleteBoard(String boardNo) {
		
		String sql = "delete tbl_board where board_no=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			pstmt.executeUpdate();
			
			System.out.println("boardDAO : " + boardNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
