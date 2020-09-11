package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.NoticeDAO;
import com.lms.dto.NoticeVO;



public class NoticeWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeVO nVo = new NoticeVO();
		
		
		nVo.setNoticeTitle(request.getParameter("noticeTitle"));
		nVo.setNoticeContent(request.getParameter("noticeContent"));
		nVo.setUserNo(request.getParameter("userno"));
		nVo.setNoticePass(request.getParameter("noticePass"));
		NoticeDAO nDao = NoticeDAO.getInstance();
		nDao.insertNotice(nVo);
		new NoticeListAction().execute(request, response);
	}
}
