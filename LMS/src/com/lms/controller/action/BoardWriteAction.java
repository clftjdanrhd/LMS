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
		
		//�Խñ� ���
		BoardDAO bDao = BoardDAO.getInstance();
		int boardNo = bDao.insertBoard(bVo);
		
		// ÷������ ó��
				System.out.println("insert boardNo: " + boardNo);
				String[] filesArray = request.getParameterValues("files");
				
				ArrayList<FileVO> fileList = new ArrayList<FileVO>();

				//÷������ [] -> List add �ϴ� �۾�
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
						//÷������ DB ����
						FileDAO.getInstance().createFile(fVo, boardNo);
					}
				}
		
		new BoardListAction().execute(request, response);
	}
}
