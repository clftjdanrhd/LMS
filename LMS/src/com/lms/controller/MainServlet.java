package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.LabListDAO;
import com.lms.dao.NoticeDAO;
import com.lms.dto.LabListVO;
import com.lms.dto.NoticeVO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/main.do")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		System.out.println(" --------------------------------------------------------------------------------------");
		NoticeDAO nDao = NoticeDAO.getInstance();
		
		//실습실 사용자 리스트
		ArrayList<LabListVO> lVo = LabListDAO.getInstance().getLabList();
		request.setAttribute("labList", lVo);
		
		//공지사항 리스트
		ArrayList<NoticeVO> nVo = nDao.mainNotice();
		request.setAttribute("noticeList", nVo);
		
		// 참여자 수 가져오기
		int result = LabListDAO.getInstance().searchT();
		request.setAttribute("result", result);
		
		// 키 장소 가져오기
		String place = LabListDAO.getInstance().getKeyPlace();
		request.setAttribute("place", place);
		
		//message null 처리
		Object message =  request.getAttribute("message");
        if(message != null) {
        	String msg = message.toString();
        	request.setAttribute("msg", msg);
        }

		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		doGet(request, response);
	}

}
