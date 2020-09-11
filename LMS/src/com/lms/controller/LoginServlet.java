package com.lms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lms.dao.LabListDAO;
import com.lms.dao.UserDAO;
import com.lms.dto.LabListVO;
import com.lms.dto.UserVO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   /**
    * 
    * 
    * @see HttpServlet#HttpServlet()
    */
   public LoginServlet() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      /*
       * String url = "user/login.jsp"; HttpSession session = request.getSession(); if
       * (session.getAttribute("loginUser") != null) {// 占싱뱄옙 占싸깍옙占쏙옙 占쏙옙 占쏙옙占쏙옙占쏙옙見占� url =
       * "index.jsp"; // 占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占싱듸옙占싼댐옙. }
       */
      RequestDispatcher dispatcher = request.getRequestDispatcher("user/login.jsp");
      dispatcher.forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      String url = "/main.do";
      
      String userno = request.getParameter("userno");
      String userpw = request.getParameter("userpw");
      UserDAO mDao = UserDAO.getInstance();
      int result = mDao.userCheck(userno, userpw);
      if (result == 1) {
         UserVO mVo = mDao.getUser(userno);
         
         
         HttpSession session = request.getSession();
         session.setAttribute("loginUser", mVo);
         
         //기존 참여/미참여 lab info 가져오기
         LabListVO lVo = LabListDAO.getInstance().getLab(userno);
         
         if(lVo != null) {
        	 
        	 session.setAttribute("labUser", lVo);
         }
         
         request.setAttribute("message", "로그인 되었습니다.");
         url = "/main.do";
      } else if (result == 0) {
         request.setAttribute("message", "아이디가 틀렸습니다.");
      } else if (result == -1) {
         request.setAttribute("message", "비밀번호가 틀렸습니다.");
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
      dispatcher.forward(request, response);
   }

}