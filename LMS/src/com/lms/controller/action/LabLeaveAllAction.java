package com.lms.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LabListDAO;
import com.lms.dto.LabListVO;

public class LabLeaveAllAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

			LabListVO lVo = new LabListVO();
			LabListDAO.getInstance().leaveAll(lVo);
			
			//키 장소 저장하기
			new LabLeaveReturnFormAction().execute(request, response);
			
		}
			
}
