package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dto.BoardVO;

public class BoardCheckPassAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String boardNo = request.getParameter("boardNo");
		System.out.println("BoardCheckPassAction : " + boardNo);
		String boardPass = request.getParameter("boardPass");
		System.out.println("BoardCheckPassAction pass : " + boardPass);
		
		BoardDAO bDao = BoardDAO.getInstance();
		BoardVO bVo = bDao.selectOneBoardByNum(boardNo);
		
		System.out.println("boardno : " + bVo);
                  	
       if (bVo.getBoardPass().equals(boardPass)) { // 성공
    	   
    	   request.setAttribute("boardNo", boardNo);
    	   new BoardDeleteAction().execute(request, response);
    	   
		} else {// 실패
			
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
			request.setAttribute("boardNo", boardNo);
			new BoardCheckPassFormAction().execute(request, response);
		}
	}
}
