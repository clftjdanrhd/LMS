package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LabListDAO;
import com.lms.dto.KeyVO;
import com.lms.dto.LabListVO;

public class LabLeaveReturnAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LabListVO lVo = new LabListVO();
		HttpSession session = request.getSession();
		lVo = (LabListVO) session.getAttribute("labUser");

		// 키 장소 저장하기
		String placeType = request.getParameter("placeType");
		String place = request.getParameter("place") == null ? "" : request.getParameter("place");
		
		System.out.println("placeType" + placeType);

		KeyVO kVo = new KeyVO();

		if (!place.equals("")) {
			kVo.setPlace(place);

		} else {
			kVo.setPlace(placeType);
		}

		kVo.setLabListNo(lVo.getLabListNo());
		//퇴실
		LabListDAO.getInstance().leaveLab(lVo);
		//반납장소
		LabListDAO.getInstance().insertKey(kVo);
		
		//labSession 삭제
		session.removeAttribute("labUser");
       	 
		response.sendRedirect("/main.do");
		
	}

}
