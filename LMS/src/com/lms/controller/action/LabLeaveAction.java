package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

public class LabLeaveAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		LabListVO lVo = new LabListVO();
		
		String userNo = request.getParameter("userNo");
		String result = LabListDAO.getInstance().searchAuthority(userNo);
		
		lVo.setUserNo(request.getParameter("userNo"));
		
		if(result.equals("1")) {
			
			LabListDAO.getInstance().leaveLab(lVo);
			
			HttpSession session = request.getSession();
			session.removeAttribute("labUser");
			
			response.sendRedirect("/main.do");
		}
		
		if(result.equals("2")) {
			
			int cnt = LabListDAO.getInstance().searchT();
			
			if(cnt == 1) {
				
				new LabLeaveReturnFormAction().execute(request, response);
				
			}else if(cnt > 1){
				
				request.setAttribute("message", "키를 반환하고 퇴실해주세요.");
				new LabListAction().execute(request, response);
			}
			
		}
			
	}

}
