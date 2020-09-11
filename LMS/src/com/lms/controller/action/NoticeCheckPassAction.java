package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.NoticeDAO;
import com.lms.dto.NoticeVO;



public class NoticeCheckPassAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String noticeNo = request.getParameter("noticeNo");
		System.out.println("NoticeCheckPassAction : " + noticeNo);
		String noticePass = request.getParameter("noticePass");
		System.out.println("NoticeCheckPassAction pass : " + noticePass);
		
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = nDao.selectOneNoticeByNum(noticeNo);
		
		System.out.println("noticeno : " + nVo);
                  	
       if (nVo.getNoticePass().equals(noticePass)) { // 성공
    	   
    	   request.setAttribute("noticeNo", noticeNo);
    	   new NoticeDeleteAction().execute(request, response);
    	   
		} else {// 실패
			
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
			request.setAttribute("noticeNo", noticeNo);
			new NoticeCheckPassFormAction().execute(request, response);
		}
	}
}
