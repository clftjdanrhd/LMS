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
       * (session.getAttribute("loginUser") != null) {// �̹� �α��� �� ������̸� url =
       * "index.jsp"; // ���� �������� �̵��Ѵ�. }
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
      String url = "user/login.jsp";
      String userno = request.getParameter("userno");
      String userpw = request.getParameter("userpw");
      UserDAO mDao = UserDAO.getInstance();
      int result = mDao.userCheck(userno, userpw);
      if (result == 1) {
         UserVO mVo = mDao.getUser(userno);
         
         
         HttpSession session = request.getSession();
         session.setAttribute("loginUser", mVo);
         request.setAttribute("message", "ȸ�����Կ� �����߽��ϴ�.");
         url = "index.jsp";
      } else if (result == 0) {
         request.setAttribute("message", "��й�ȣ�� ���� �ʽ��ϴ�.");
      } else if (result == -1) {
         request.setAttribute("message", "�������� �ʴ� ȸ���Դϴ�.");
      }
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
      dispatcher.forward(request, response);
   }

}