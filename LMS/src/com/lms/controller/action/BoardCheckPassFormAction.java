package com.lms.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dto.BoardVO;

public class BoardCheckPassFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "/board/boardCheckPass.jsp";
		
		
		String boardNo = request.getParameter("boardNo");
		System.out.println("BoardCheckPassFormAction : " + boardNo);
		
		List<BoardVO> bVo = BoardDAO.getInstance().catchBoardNo(boardNo);
		request.setAttribute("board", bVo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
