package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.NoticeDAO;




public class NoticeDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String noticeNo=request.getParameter("noticeNo");
		request.setAttribute("noticeNo", noticeNo);
		
		System.out.println("NoticeDeleteAction : " + noticeNo);
		
		
		NoticeDAO nDao=NoticeDAO.getInstance();
		nDao.deleteNotice(noticeNo);
		
		
		new NoticeListAction().execute(request, response);
	}
}
