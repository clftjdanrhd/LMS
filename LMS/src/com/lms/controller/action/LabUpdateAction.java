package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

public class LabUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		
		LabListDAO lDao = LabListDAO.getInstance();
		
		// 일반 > 마스터
		LabListVO lVo = new LabListVO();
		lVo.setUserNo(request.getParameter("userNo"));
		
		System.out.println("userNo : " + lVo.getUserNo());
		
		lDao.labUpdateMaster(lVo);
		
		// 일반 < 마스터
		LabListVO lVo2 = new LabListVO();
		
		lVo2.setUserNo(request.getParameter("masterNo"));
		System.out.println("masterNo : " + lVo2.getUserNo());
		
		lDao.labUpdateMember(lVo2);
		
		HttpSession session = request.getSession();
		lVo2 = (LabListVO) session.getAttribute("labUser");
		session.removeAttribute("labUser");
		
		lVo2 = LabListDAO.getInstance().getLab(lVo2.getUserNo());
		session.setAttribute("labUser", lVo2);
		
		new LabListAction().execute(request, response);
	}

}
