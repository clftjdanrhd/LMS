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
       
   protected void doGet(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
      
	   String url="user/login.jsp";
      
      HttpSession session= request.getSession();
      
      if(session.getAttribute("loginUser") !=null){//�씠誘� 濡쒓렇�씤�맂 �궗�슜�옄硫� 
    	  url="main.jsp";//硫붿씤 �럹�씠吏�濡� �씠�룞�븳�떎
      }
      
	   RequestDispatcher dispatcher = request
            .getRequestDispatcher(url);
      dispatcher.forward(request, response);
   }
   

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url="user/login.jsp";
            
            String userno= request.getParameter("userno");
      String userpw= request.getParameter("userpw");
      
      UserDAO mDao= UserDAO.getInstance();
      int result = mDao.userCheck(userno,userpw);
      
      if(result==1){
         UserVO mVo= mDao.getMember(userno);
         HttpSession session = request.getSession();
         session.setAttribute("loginUser", mVo);
         request.setAttribute("message", "회원가입에 성공했습니다");
         url="main.jsp";
      }else if(result==0){
         request.setAttribute("message", "비밀번호가 맞지 않습니다 ");
      }else if(result==-1){
         request.setAttribute("message", "존재하지 않는 회원입니다");
      }
   RequestDispatcher dispatcher= request
         .getRequestDispatcher(url);
   dispatcher.forward(request, response);
   
   }
   

}