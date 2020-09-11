package com.lms.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.FileDAO;
import com.lms.dto.FileVO;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class FileAttachAjax
 */
@WebServlet("/getBoardAttach")
public class BoardFileAttachAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFileAttachAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		System.out.println("/getBoardAttach");

		String boardNo = request.getParameter("boardNo") == null ? "" : request.getParameter("boardNo");
		

		if (!boardNo.equals("")) {
			
			System.out.println("getAttach boardNo : " + boardNo);

			List<FileVO> fileList = null;

			fileList = FileDAO.getInstance().getFileList(boardNo);
			

			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

			PrintWriter pw = response.getWriter();

			JSONArray jsonArray = JSONArray.fromObject(fileList);

			System.out.println(jsonArray);
			pw.print(jsonArray);

			pw.flush();
			pw.close();

		} 

	}

}
