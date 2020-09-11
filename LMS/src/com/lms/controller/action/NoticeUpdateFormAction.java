package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.NoticeDAO;
import com.lms.dto.NoticeVO;

public class NoticeUpdateFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String url= "/notice/noticeUpdate.jsp";
		String noticeNo = request.getParameter("noticeNo");
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO nVo = nDao.selectOneNoticeByNum(noticeNo);
		request.setAttribute("notice", nVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		}
	}

