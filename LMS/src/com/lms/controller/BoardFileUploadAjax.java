package com.lms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.EmpUploadFileUtils;
import util.MediaUtils;

/**
 * Servlet implementation class FileUploadAjax
 */
@WebServlet("/uploadBoardAjax")
public class BoardFileUploadAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String uploadFilePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardFileUploadAjax() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("uploadBoardAjax");
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
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	private ResponseEntity<String> process(HttpServletRequest request, HttpServletResponse response) throws Exception {

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		int uploadFileSizeLimit = 5 * 1024 * 1024;

		String encType = "UTF-8";
		uploadFilePath = request.getServletContext().getRealPath("/") + "upload/";

		System.out.println(uploadFilePath);

		MultipartRequest multi = new MultipartRequest(
				request, 
				uploadFilePath, 
				uploadFileSizeLimit, 
				encType, 
				new DefaultFileRenamePolicy());

		String name = "file";

		String res = EmpUploadFileUtils.uploadFile(uploadFilePath, multi.getOriginalFileName(name),
				multi.getFilesystemName(name).getBytes());

		System.out.println("process :  " + res);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter pw = response.getWriter();
		pw.print(res);
		pw.flush();
	}
	

	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {

			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadFilePath + fileName);

			if (mType != null) {
				headers.setContentType(mType);
			} else {

				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition",
						"attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}

			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	
}
