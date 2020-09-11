package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dao.FileDAO;
import com.lms.dto.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		
		String boardNo = request.getParameter("boardNo");
		BoardDAO bDao = BoardDAO.getInstance();
		
		System.out.println("BoardViewAction : " + boardNo );

		BoardVO bVo = bDao.selectOneBoardByNum(boardNo);
		bDao.updateReadCount(boardNo);
		System.out.println("bVo : " + bVo);
		
		//첨부파일 가져오기
		bVo.setFileList(FileDAO.getInstance().getFileList(boardNo));
		
		request.setAttribute("board", bVo);
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
