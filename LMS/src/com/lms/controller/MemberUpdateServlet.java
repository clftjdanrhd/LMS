package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.UserDAO;
import com.lms.dto.UserVO;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		UserDAO mDao= UserDAO.getInstance();
		
		UserVO mVo= mDao.getUser(userno);
		request.setAttribute("mVo", mVo);
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("member/memberUpdate.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//�븳湲�源⑥쭚 諛⑹�
		//�뤌�뿉�꽌 �엯�젰�븳 �쉶�썝�젙蹂� �뼸�뼱�삤湲�
		Integer userno = Integer.parseInt("userno");
		String userpw = request.getParameter("userpw");
		String username = request.getParameter("username");
		Integer usertel = Integer.parseInt("usertel");
		String state = request.getParameter("state");
		String authority = request.getParameter("authority");
		//�쉶�썝�젙蹂대�� ���옣�븷 媛앹껜 �깮�꽦 
		UserVO mVo= new UserVO();
		mVo.setUserNo(userno);
		mVo.setUserPw);
		mVo.setUserName(email);
		mVo.setPhone(phone);
		mVo.setAdmin(Integer.parseInt(admin));
		
		MemberDAO mDao = MemberDAO.getInstance();
		
		mDao.updateMember(mVo);
		
		response.sendRedirect("login.do");
		
		
	}

}
