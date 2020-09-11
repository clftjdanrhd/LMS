package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dto.BoardVO;

public class BoardUpdateFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/board/boardUpdate.jsp";
		String boardNo = request.getParameter("boardNo");
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateReadCount(boardNo);
		
		BoardVO bVo = bDao.selectOneBoardByNum(boardNo);
		System.out.println("bVo : " + bVo);
		
		request.setAttribute("board", bVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
