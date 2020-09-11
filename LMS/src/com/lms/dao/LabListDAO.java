package com.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.lms.dto.KeyVO;
import com.lms.dto.LabListVO;

import util.DBManager;
import util.SearchCriteria;

public class LabListDAO {
	
	   private LabListDAO() {
		   
	   }
	     private static LabListDAO instance = new LabListDAO();

	      public static LabListDAO getInstance() {
	         return instance;
	      }
	      
	      //실습실 내역 가져오기
	      public ArrayList<LabListVO> ExcelDownLoad() {
	    	  String sql = "SELECT L.USER_NO, U.USER_NAME, L.STARTTIME, L.ENDTIME, L.STATE"
                      	 + " FROM TBL_LAB_LIST L, TBL_USER U";


	           System.out.println(sql);

	           ArrayList<LabListVO> list = new ArrayList<LabListVO>();
	           Connection conn = null;
	           Statement stmt = null;
	           ResultSet rs = null;
	           
	           try {
	              conn = DBManager.getConnection();
	              stmt = conn.createStatement();
	              rs = stmt.executeQuery(sql);
	              
	              while (rs.next()) {
	            	  
	            	  LabListVO lVo = new LabListVO();
	                  
	            	  lVo.setUserNo(rs.getString("user_no"));
	            	  lVo.setStartTime(rs.getString("startTime"));
	            	  lVo.setEndTime(rs.getString("endTime"));
	            	  lVo.setState(rs.getString("state"));
	            	  lVo.setUserName(rs.getString("user_name"));

	                 list.add(lVo);
	              }
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              DBManager.close(conn, stmt, rs);
	           }
	           return list;
	        }
	      
	      
	      //키 장소 가져오기
	      public String getKeyPlace() {
	    	  
		    	String place="";
		  		
		  		String sql = "select place from tbl_key where place is not null and rownum = 1 order by key_no desc";
		  		
		  		Connection conn = null;
		  		PreparedStatement pstmt = null;
		  		ResultSet rs = null;
		  		
		  		try {
		  			
		  			conn = DBManager.getConnection();
		  			pstmt = conn.prepareStatement(sql);
		  			rs = pstmt.executeQuery();
		  			
		  			if (rs.next()) {
		  				
		  				place = rs.getString("place");
		  			
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
		  		return place;
		  		
		  	}
	      
	      //실습실 내역 가져오기
	      public ArrayList<LabListVO> getLabList() {
	    	  String sql = "SELECT L.USER_NO, L.STARTTIME, L.ENDTIME, L.AUTHORITY, L.LAB_LIST_NO, L.STATE, U.USER_NAME"
	    			     +" FROM TBL_LAB_LIST L, TBL_USER U"
	    			     +" WHERE L.USER_NO = U.USER_NO"
	    			     +" AND L.STATE = 'T'"
	    			     + " ORDER BY AUTHORITY DESC";


	           System.out.println(sql);

	           ArrayList<LabListVO> list = new ArrayList<LabListVO>();
	           Connection conn = null;
	           Statement stmt = null;
	           ResultSet rs = null;
	           
	           try {
	              conn = DBManager.getConnection();
	              stmt = conn.createStatement();
	              rs = stmt.executeQuery(sql);
	              
	              while (rs.next()) {
	            	  
	            	  LabListVO lVo = new LabListVO();
	                  
	            	  lVo.setUserNo(rs.getString("user_no"));
	            	  lVo.setStartTime(rs.getString("startTime"));
	            	  lVo.setEndTime(rs.getString("endTime"));
	            	  lVo.setAuthority(rs.getString("authority"));
	            	  lVo.setState(rs.getString("state"));
	            	  lVo.setLabListNo(rs.getInt("lab_list_no"));
	            	  lVo.setUserName(rs.getString("user_name"));

	                 list.add(lVo);
	              }
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              DBManager.close(conn, stmt, rs);
	           }
	           return list;
	        }
	      
	      //키 장소 저장하기
	      public void insertKey(KeyVO kVo) {
	    	  
	    	  String sql = "INSERT INTO TBL_KEY(KEY_NO, PLACE, LAB_LIST_NO)"
	    			  	+  " VALUES(KEY_SEQ.NEXTVAL, ?, ?)";
	            Connection conn = null;
	            PreparedStatement pstmt = null;
	            try {
	            	
	               conn = DBManager.getConnection();
	               pstmt = conn.prepareStatement(sql);
	               pstmt.setString(1, kVo.getPlace());
	               pstmt.setInt(2, kVo.getLabListNo());
	               
	               pstmt.executeUpdate();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            } finally {
	               DBManager.close(conn, pstmt);
	            }
	         }
	      
	      //실습실 정보(세션)
	      public LabListVO getLab(String userNo) {
	    	  
	    	LabListVO lVo = null;
	  		
	  		String sql = "select * from TBL_LAB_LIST where USER_NO=?"
	  				   + " and state ='T'";
	  		
	  		Connection conn = null;
	  		PreparedStatement pstmt = null;
	  		ResultSet rs = null;
	  		
	  		try {
	  			
	  			conn = DBManager.getConnection();
	  			pstmt = conn.prepareStatement(sql);
	  			pstmt.setString(1, userNo);
	  			rs = pstmt.executeQuery();
	  			
	  			if (rs.next()) {
	  				
	  				lVo = new LabListVO();
	  				lVo.setLabListNo(rs.getInt("LAB_LIST_NO"));
	  				lVo.setUserNo(rs.getString("user_no"));
	            	lVo.setStartTime(rs.getString("startTime"));
	            	lVo.setEndTime(rs.getString("endTime"));
	  				lVo.setAuthority(rs.getString("authority"));
	  				lVo.setState(rs.getString("state"));
	  			
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
	  		return lVo;
	  	}
	      
	      
	      //입실자가 없을 때  사용자 내역 등록
	      public void insertLabListT(LabListVO lVo) {
	    	  String sql = "INSERT INTO TBL_LAB_LIST(LAB_LIST_NO, AUTHORITY, STATE, USER_NO)"  
	      	  		        + "VALUES (LAB_SEQ.NEXTVAL, '2', 'T', ?)";
	  		Connection conn = null;
	  		PreparedStatement pstmt = null;
	  		try {
	  			conn = DBManager.getConnection();
	  			pstmt = conn.prepareStatement(sql);
	  			
	  			pstmt.setString(1, lVo.getUserNo());
	  			
	  			pstmt.executeUpdate();
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		} finally {
	  			DBManager.close(conn, pstmt);
	  		}
	  	}
	      
	      //입실자가 이미 있을 때 사용자 내역 등록
	      public void insertLabListF(LabListVO lVo) {
	    	  String sql = "INSERT INTO TBL_LAB_LIST(LAB_LIST_NO, AUTHORITY, STATE, USER_NO)"  
	      	  		        + "VALUES (LAB_SEQ.NEXTVAL, '1', 'T', ?)";
	  		Connection conn = null;
	  		PreparedStatement pstmt = null;
	  		try {
	  			conn = DBManager.getConnection();
	  			pstmt = conn.prepareStatement(sql);
	  			
	  			pstmt.setString(1, lVo.getUserNo());
	  			
	  			pstmt.executeUpdate();
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		} finally {
	  			DBManager.close(conn, pstmt);
	  		}
	  	}
	      
	      //사용자 내역 리스트 페이징
	      
	      public int listSearchCount(SearchCriteria cri) {

	          String sql = "SELECT COUNT(*) AS CNT FROM("
	    		      	 +" SELECT ROWNUM AS RNUM, TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI') AS STARTTIME, TO_CHAR(ENDTIME, 'YYYY-MM-DD HH24:MI') AS ENDTIME, AUTHORITY, STATE, USER_NO, USER_NAME"
	    		      	 +" FROM (SELECT L.STARTTIME, L.ENDTIME, L.AUTHORITY, L.STATE, U.USER_NO, U.USER_NAME"
	    		      	 +" FROM TBL_LAB_LIST L , TBL_USER U"
	    		      	 +" WHERE L.USER_NO = U.USER_NO"
	    		      	 +" ORDER BY L.LAB_LIST_NO DESC))";

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
	      
	      public ArrayList<LabListVO> selectAllLabList(SearchCriteria cri) {
	    	  String sql = "SELECT * FROM("
		    		     +" SELECT ROWNUM AS RNUM, TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI') AS STARTTIME, TO_CHAR(ENDTIME, 'YYYY-MM-DD HH24:MI') AS ENDTIME, AUTHORITY, STATE, USER_NO, USER_NAME"
		    		     +" FROM (SELECT L.STARTTIME, L.ENDTIME, L.AUTHORITY, L.STATE, U.USER_NO, U.USER_NAME"
		    		     +" FROM TBL_LAB_LIST L , TBL_USER U" 
                      	 +" WHERE L.USER_NO = U.USER_NO"
		    		     +" )"
		    		     +" ORDER BY ENDTIME DESC)";  

		      String pagingQuery = " WHERE rnum >= " + cri.getPageStart() + " AND rnum <= " + (cri.getPageStart()+cri.getPerPageNum()-1);
		    		  
	           sql += pagingQuery;

	           System.out.println(sql);

	           ArrayList<LabListVO> list = new ArrayList<LabListVO>();
	           Connection conn = null;
	           Statement stmt = null;
	           ResultSet rs = null;
	           
	           try {
	              conn = DBManager.getConnection();
	              stmt = conn.createStatement();
	              rs = stmt.executeQuery(sql);
	              
	              while (rs.next()) {
	            	  
	            	  LabListVO lVo = new LabListVO();
	                  
	            	  lVo.setUserNo(rs.getString("user_no"));
	            	  lVo.setStartTime(rs.getString("startTime"));
	            	  lVo.setEndTime(rs.getString("endTime"));
	            	  lVo.setAuthority(rs.getString("authority"));
	            	  lVo.setState(rs.getString("state"));
	            	  lVo.setUserName(rs.getString("user_name"));

	                 list.add(lVo);
	              }
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              DBManager.close(conn, stmt, rs);
	           }
	           return list;
	        }
	      
	      
	      public ArrayList<LabListVO> LabListT(SearchCriteria cri) {
	    	  String sql = "SELECT * FROM("
		    		     +" SELECT ROWNUM AS RNUM, TO_CHAR(STARTTIME,'YYYY-MM-DD HH24:MI') AS STARTTIME, TO_CHAR(ENDTIME, 'YYYY-MM-DD HH24:MI') AS ENDTIME, AUTHORITY, STATE, USER_NO, USER_NAME"
		    		     +" FROM (SELECT L.STARTTIME, L.ENDTIME, L.AUTHORITY, L.STATE, U.USER_NO, U.USER_NAME"
		    		     +" FROM TBL_LAB_LIST L , TBL_USER U" 
                      	 +" WHERE L.USER_NO = U.USER_NO"
                      	 +" AND L.STATE = 'T'"
		    		     +" ORDER BY L.AUTHORITY DESC))";

	           System.out.println(sql);

	           ArrayList<LabListVO> list = new ArrayList<LabListVO>();
	           Connection conn = null;
	           Statement stmt = null;
	           ResultSet rs = null;
	           
	           try {
	              conn = DBManager.getConnection();
	              stmt = conn.createStatement();
	              rs = stmt.executeQuery(sql);
	              
	              while (rs.next()) {
	            	  
	            	  LabListVO lVo = new LabListVO();
	                  
	            	  lVo.setUserNo(rs.getString("user_no"));
	            	  lVo.setStartTime(rs.getString("startTime"));
	            	  lVo.setEndTime(rs.getString("endTime"));
	            	  lVo.setAuthority(rs.getString("authority"));
	            	  lVo.setState(rs.getString("state"));
	            	  lVo.setUserName(rs.getString("user_name"));

	                 list.add(lVo);
	              }
	           } catch (SQLException e) {
	              e.printStackTrace();
	           } finally {
	              DBManager.close(conn, stmt, rs);
	           }
	           return list;
	        }

	      
	      // 'T(입실중에 있는 사람)'인 사람 카운트
	      public int searchT() {
	    	  String sql = " SELECT COUNT(*) AS CNT FROM TBL_LAB_LIST" 
	    	  		     + " WHERE STATE = 'T'";
	    	  
	   			Connection conn = null;
	   			PreparedStatement pstmt = null;
	   			ResultSet rs = null;
	   			
	   			int cnt = 0;
	   			
		   		try {
		   			
		   			conn = DBManager.getConnection();
		   			pstmt = conn.prepareStatement(sql);
		   			rs = pstmt.executeQuery();
		   			
		   			if (rs.next()) {
		   				
		   			 cnt = rs.getInt("CNT");
		   				
		   			}
		   		} catch (Exception e) {
		   			e.printStackTrace();
		   		} finally {
		   			DBManager.close(conn, pstmt, rs);
		   		}
		   		return cnt;
		   	}
	      
	      
	      	
	      // 'T'(입실중에 있는 사람)'인 사람 카운트 
	      public String searchAuthority(String userNo) {
	    	  String sql = " SELECT AUTHORITY FROM TBL_LAB_LIST" 
	    	  		     + " WHERE USER_NO = ?"
	    	  		     + " AND STATE = 'T'";
	    	  
	   			Connection conn = null;
	   			PreparedStatement pstmt = null;
	   			ResultSet rs = null;
	   			
	   			String authority = null;
	   			
		   		try {
		   			
		   			conn = DBManager.getConnection();
		   			pstmt = conn.prepareStatement(sql);
		   			pstmt.setString(1, userNo);
		   			rs = pstmt.executeQuery();
		   			
		   			if (rs.next()) {
		   			authority = rs.getString("authority");
		   			}
		   		} catch (Exception e) {
		   			e.printStackTrace();
		   		} finally {
		   			DBManager.close(conn, pstmt, rs);
		   		}
		   		return authority;
		   	}
		      
	      
	      //사용자 내역 권한 설정
			
		public void labUpdateMaster(LabListVO lVo)  {
			
		    String sql = "UPDATE TBL_LAB_LIST SET AUTHORITY = 2" 
		    		   + " WHERE USER_NO = ?"
		    		   + " AND STATE = 'T'";
		    
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      
		      try {
		    	  
		         conn = DBManager.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, lVo.getUserNo());
		         
		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         DBManager.close(conn, pstmt);
		      }
		   }
		
		//마스터 > 일반회원
		public void labUpdateMember(LabListVO lVo)  {
					
				    String sql = "UPDATE TBL_LAB_LIST SET AUTHORITY = 1" 
				    		   + " WHERE USER_NO = ?"
				    		   + " AND STATE = 'T'";
				    
				      Connection conn = null;
				      PreparedStatement pstmt = null;
				      
				      try {
				    	  
				         conn = DBManager.getConnection();
				         pstmt = conn.prepareStatement(sql);
				         
				         pstmt.setString(1, lVo.getUserNo());
				         
				         pstmt.executeUpdate();
				      } catch (SQLException e) {
				         e.printStackTrace();
				      } finally {
				         DBManager.close(conn, pstmt);
				      }
				   }
			
	      
	      //사용자의 일반,키마스터 혼자일 때 퇴실 상태
		public void leaveLab(LabListVO lVo)  {
			
		    String sql = "UPDATE TBL_LAB_LIST SET STATE = 'F',"
		    		   + " AUTHORITY = 1, ENDTIME = SYSDATE" 
		    		   + " WHERE USER_NO = ?"
		    		   + " AND STATE = 'T'";
		                     
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      
		      try {
		    	  
		         conn = DBManager.getConnection();
		         pstmt = conn.prepareStatement(sql);
		         
		         pstmt.setString(1, lVo.getUserNo());
		         
		         pstmt.executeUpdate();
		      } catch (SQLException e) {
		         e.printStackTrace();
		      } finally {
		         DBManager.close(conn, pstmt);
		      }
		   }
		
		      //전체 퇴실
			public void leaveAll(LabListVO lVo)  {
				
			    String sql = " UPDATE TBL_LAB_LIST SET STATE = 'F', ENDTIME = SYSDATE, AUTHORITY = 1"
			    		   + " WHERE STATE = 'T'";
			                     
			      Connection conn = null;
			      PreparedStatement pstmt = null;
			      
			      try {
			    	  
			         conn = DBManager.getConnection();
			         pstmt = conn.prepareStatement(sql);
			         pstmt.executeUpdate();
			         
			      } catch (SQLException e) {
			    	  
			         e.printStackTrace();
			         
			      } finally {
			    	  
			         DBManager.close(conn, pstmt);
			         
			      }
			   }
			
	}
	      
	      

