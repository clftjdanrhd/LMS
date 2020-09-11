package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.lms.dto.NoticeVO;

import util.DBManager;
import util.SearchCriteria;

public class NoticeDAO {
   private NoticeDAO() {
   }
     private static NoticeDAO instance = new NoticeDAO();

      public static NoticeDAO getInstance() {
         return instance;
      }
      
      
      
      
      public List<NoticeVO> catchNoticeNo(String noticeNo) {
    	  
    	  String sql = "select notice_no from tbl_notice where notice_no = ?";
    	  
    	  
    	  List<NoticeVO> list = new ArrayList<NoticeVO>(); 
    	  Connection conn = null;
    	  PreparedStatement pstmt = null; 
    	  ResultSet rs = null;
    	  NoticeVO nVo =  null;
    	  
    	  try { 
    		  conn = DBManager.getConnection();
    			pstmt = conn.prepareStatement(sql);
    			pstmt.setString(1, noticeNo);
    			rs = pstmt.executeQuery();
    			
    			if (rs.next()) {
    				nVo = new NoticeVO();
    				nVo.setNoticeNo(rs.getInt("notice_no"));
    				list.add(nVo); 
    				
    				System.out.println("noticeDao : " + list);
    	  } 
    	  } catch (SQLException e) {
    		  e.printStackTrace(); 
    		  } finally { 
    			  DBManager.close(conn,pstmt, rs); 
    			  
    			  } return list; 
    			  
    	  }
      
      
      
      public int listSearchCount(SearchCriteria cri) {

          String sql = "SELECT COUNT(*) AS CNT FROM"
                    +" (SELECT ROWNUM AS RNUM, B.NOTICE_NO, B.NOTICE_TITLE, B.REGDATE, B.VIEWCNT, U.USER_NAME"
                    +" FROM TBL_NOTICE B, TBL_USER U"
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

      //������ List
      public ArrayList<NoticeVO> selectAllNotices(SearchCriteria cri) {
    	  String sql = " SELECT * FROM("
	    		     +" SELECT ROWNUM AS RNUM, NOTICE_NO, NOTICE_TITLE,REGDATE, VIEWCNT,USER_NAME"
	    		     +" FROM (SELECT B.NOTICE_NO, B.NOTICE_TITLE, B.REGDATE, B.VIEWCNT, U.USER_NAME"
	    		     +" FROM TBL_NOTICE B, TBL_USER U WHERE B.USER_NO = U.USER_NO"
	    		     +" ORDER BY B.NOTICE_NO DESC))";
               

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

           ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
           Connection conn = null;
           Statement stmt = null;
           ResultSet rs = null;
           try {
              conn = DBManager.getConnection();
              stmt = conn.createStatement();
              rs = stmt.executeQuery(sql);
              while (rs.next()) {
                 NoticeVO nVo = new NoticeVO();
                 nVo.setNoticeNo(rs.getInt("notice_no"));
                 nVo.setNoticeTitle(rs.getString("notice_title"));
                 nVo.setRegDate(rs.getDate("regDate"));
                 nVo.setViewCnt(rs.getInt("viewcnt"));
                 nVo.setUserName(rs.getString("user_name"));

                 list.add(nVo);
              }
           } catch (SQLException e) {
              e.printStackTrace();
           } finally {
              DBManager.close(conn, stmt, rs);
           }
           return list;
        }
     
      	
      public ArrayList<NoticeVO> mainNotice() {
    	  
  	    String sql = "SELECT n.notice_title, u.user_name, n.notice_no" 
  	    		   +" FROM TBL_NOTICE n ,TBL_USER u"
  	    		   +" WHERE n.user_no = u.user_no"
  	    		   +" ORDER BY NOTICE_NO DESC";
       
  	    ArrayList<NoticeVO> list = new ArrayList<NoticeVO>();
         Connection conn = null;
         Statement pstmt = null;
         ResultSet rs = null;
         try {
            conn = DBManager.getConnection();
            pstmt = conn.createStatement();
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
               NoticeVO nVo = new NoticeVO();
               nVo.setNoticeTitle(rs.getString("notice_title"));
               nVo.setUserName(rs.getString("user_name"));
               nVo.setNoticeNo(rs.getInt("notice_no"));

               list.add(nVo);
            }
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            DBManager.close(conn, pstmt, rs);
         }
         return list;
      }

  
      
      
      //������ ���
      public void insertNotice(NoticeVO nVo) {
    	  String sql = "insert into tbl_notice("
  				+ "notice_no, notice_title, notice_content, regdate, user_no, notice_pass) "
  				+ "values(notice_seq.nextval, ?, ?, sysdate, ?, ?)";
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		try {
  			conn = DBManager.getConnection();
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, nVo.getNoticeTitle());
  			pstmt.setString(2, nVo.getNoticeContent());
  			pstmt.setString(3, nVo.getUserNo());
  			pstmt.setString(4, nVo.getNoticePass());
  			pstmt.executeUpdate();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBManager.close(conn, pstmt);
  		}
  	}
      
      
      //������ ����
      public void updateNotice(NoticeVO nVo)  {
          String sql = "update tbl_notice set notice_title=?, notice_content=?, notice_pass=? "
                  + "  where notice_no=?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
               conn = DBManager.getConnection();
               pstmt = conn.prepareStatement(sql);
               pstmt.setString(1, nVo.getNoticeTitle());
               pstmt.setString(2, nVo.getNoticeContent());
               pstmt.setString(3, nVo.getNoticePass());
               pstmt.setInt(4, nVo.getNoticeNo());
               pstmt.executeUpdate();
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
               DBManager.close(conn, pstmt);
            }
         }


      
    //������ ����
      public void deleteNotice(String noticeNo) {
         String sql = "delete tbl_notice where notice_no=?";
         Connection conn = null;
         PreparedStatement pstmt = null;
         try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, noticeNo);
            pstmt.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      
  
      
   // �Խ��� �� �� ���� ���� :�۹�ȣ�� ã�ƿ´�. : ���� null,
      public NoticeVO selectOneNoticeByNum(String noticeNo) {
    	  
    		String sql = "select b.notice_no, b.notice_title, b.notice_content, b.regdate, b.viewcnt, u.user_name, b.notice_pass" 
 				   + " from tbl_notice b, tbl_user u"  
 				   + " where b.user_no = u.user_no"
 				   + " and notice_no = ?";
 		
    		NoticeVO nVo = null;
 		Connection conn = null;
 		PreparedStatement pstmt = null;
 		ResultSet rs = null;
 		try {
 			conn = DBManager.getConnection();
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setString(1, noticeNo);
 			rs = pstmt.executeQuery();
 			if (rs.next()) {
 				nVo = new NoticeVO();
 				nVo.setNoticeNo(rs.getInt("notice_no"));
 				nVo.setNoticeTitle(rs.getString("notice_title"));
 				nVo.setNoticeContent(rs.getString("notice_content"));
 				nVo.setRegDate(rs.getDate("regdate"));
 				nVo.setViewCnt(rs.getInt("viewcnt"));
 				nVo.setUserName(rs.getString("user_name"));
 				nVo.setNoticePass(rs.getString("notice_pass"));
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		} finally {
 			DBManager.close(conn, pstmt, rs);
 		}
 		return nVo;
 	}
      public void updateReadCount(String noticeNo) {
  		String sql = "update tbl_notice set viewcnt=viewcnt+1 where notice_no=?";
  		Connection conn = null;
  		PreparedStatement pstmt = null;
  		try {
  			conn = DBManager.getConnection();
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setString(1, noticeNo);
  			pstmt.executeUpdate();
  		} catch (SQLException e) {
  			e.printStackTrace();
  		} finally {
  			DBManager.close(conn, pstmt);
  		}
  	}

      
      

    }