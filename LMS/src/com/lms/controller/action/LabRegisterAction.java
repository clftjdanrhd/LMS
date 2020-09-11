package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

public class LabRegisterAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		LabListVO lVo = new LabListVO();
		
		String userNo = request.getParameter("userNo");
		
		int result = LabListDAO.getInstance().searchT();
		lVo.setUserNo(request.getParameter("userNo"));
		
		if(result == 0) {
			
			LabListDAO.getInstance().insertLabListT(lVo);
		}
		
		else{
			
			LabListDAO.getInstance().insertLabListF(lVo);
		}
		
		//�α��� ���� �����ͼ� lab info setAttribute();
		HttpSession session = request.getSession();
		//�� ���� ��������
		lVo = LabListDAO.getInstance().getLab(userNo);
		
        session.setAttribute("labUser", lVo);
		
        response.sendRedirect("/main.do");		
		
	}

}
