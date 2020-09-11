package com.lms.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.dao.BoardDAO;
import com.lms.dao.FileDAO;
import com.lms.dto.BoardVO;
import com.lms.dto.FileVO;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardVO bVo = new BoardVO();
		
		
		bVo.setBoardTitle(request.getParameter("boardTitle"));
		bVo.setBoardContent(request.getParameter("boardContent"));
		bVo.setUserNo(request.getParameter("userno"));
		bVo.setBoardPass(request.getParameter("boardPass"));
		
		//게시글 등록
		BoardDAO bDao = BoardDAO.getInstance();
		int boardNo = bDao.insertBoard(bVo);
		
		// 첨부파일 처리
				System.out.println("insert boardNo: " + boardNo);
				String[] filesArray = request.getParameterValues("files");
				
				ArrayList<FileVO> fileList = new ArrayList<FileVO>();

				//첨부파일 [] -> List add 하는 작업
				if (filesArray != null) {

					for (String fileName : filesArray) {

						FileVO iVo = new FileVO();
						iVo.setFileName(fileName);
						fileList.add(iVo);

						System.out.println("filesArray: " + fileName);

					}
				}

				System.out.println("Register Board: " + bVo);

				if (boardNo != 0) {
					for (FileVO fVo : fileList) {
						//첨부파일 DB 저장
						FileDAO.getInstance().createFile(fVo, boardNo);
					}
				}
		
		new BoardListAction().execute(request, response);
	}
}
