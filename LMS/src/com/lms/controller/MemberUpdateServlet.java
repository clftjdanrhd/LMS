package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.UserDAO;
import com.lms.dto.UserVO;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/memberUpdate.do")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userno = request.getParameter("userno");
		UserDAO mDao = UserDAO.getInstance();
		UserVO mVo = mDao.getUser(userno);
		request.setAttribute("mVo", mVo);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("user/memberUpdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐을 방지
		// 폼에서 입력한 회원 정보 얻어오기
		String userno = request.getParameter("userno");
		String userpw = request.getParameter("userpw");
		String state = request.getParameter("state");
		Integer usertel = Integer.getInteger("usertel");
		String authority = request.getParameter("authority");
		// 회원 정보를 저장할 객체 생성
		UserVO mVo = new UserVO();
		mVo.setUserno(userno);
		mVo.setUserpw(userpw);
		mVo.setState(state);
		mVo.setUsertel(usertel);
		mVo.setAuthority(authority);
		UserDAO mDao = UserDAO.getInstance();
		mDao.updateUser(mVo);
		response.sendRedirect("login.do");
	}

}
