package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.FileDAO;

/**
 * Servlet implementation class FileDeleteAjax
 */
@WebServlet("/deleteBoardFile")
public class BoardFileDeleteAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFileDeleteAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("deleteFile");

		String fileName = request.getParameter("fileName");
		String imgName = request.getParameter("fileName") == null ? "" : request.getParameter("fileName");

		System.out.println("Delete fileName ====> " + fileName);
		String uploadFilePath = request.getServletContext().getRealPath("/") + "upload/";

		String dFileName = request.getParameter("fileName");
		
		java.io.File file = new java.io.File(uploadFilePath + dFileName);

		boolean res = false;

		if (file.isFile()) {

			System.out.println("isFile Delete");
			res = file.delete();

		}

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter pw = response.getWriter();

		if (res) {
			
			pw.print("deleted");
			
			//deleteFile DB delete
			if(!imgName.equals("")) {
				FileDAO.getInstance().deleteFileName(dFileName);
			}
			

		}

		pw.flush();
		pw.close();  
	}

}
