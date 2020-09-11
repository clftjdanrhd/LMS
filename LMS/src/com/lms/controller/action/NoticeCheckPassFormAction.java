package com.lms.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.NoticeDAO;
import com.lms.dto.NoticeVO;



public class NoticeCheckPassFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/notice/noticeCheckPass.jsp";
		
		
		String noticeNo = request.getParameter("noticeNo");
		System.out.println("NoticeCheckPassFormAction : " + noticeNo);
		
		List<NoticeVO> nVo = NoticeDAO.getInstance().catchNoticeNo(noticeNo);
		request.setAttribute("notice", nVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
