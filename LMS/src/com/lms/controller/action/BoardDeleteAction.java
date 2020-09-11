package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dao.FileDAO;



public class BoardDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String boardNo=request.getParameter("boardNo");
		request.setAttribute("boardNo", boardNo);
		
		System.out.println("BoardDeleteAction : " + boardNo);
		
		//게시글의 첨부파일(자식) 삭제
		FileDAO.getInstance().deleteFile(boardNo);
		
		//게시글 삭제
		BoardDAO bDao=BoardDAO.getInstance();
		bDao.deleteBoard(boardNo);
		
		new BoardListAction().execute(request, response);
	}
}
