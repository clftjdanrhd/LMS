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

public class BoardUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO bVo = new BoardVO();

		String boardNo = request.getParameter("boardNo");
		bVo.setBoardNo(Integer.parseInt(boardNo));
		bVo.setBoardTitle(request.getParameter("boardTitle"));
		bVo.setBoardContent(request.getParameter("boardContent"));
		bVo.setUserName(request.getParameter("userName"));
		bVo.setBoardPass(request.getParameter("boardPass"));

		// �Խñ� ������Ʈ
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateBoard(bVo);

		// ÷������ ó��
		String[] filesArray = request.getParameterValues("files");

		ArrayList<FileVO> fileList = new ArrayList<FileVO>();

		if (filesArray != null) {

			for (String fileName : filesArray) {

				FileVO iVo = new FileVO();
				iVo.setFileName(fileName);
				fileList.add(iVo);

				System.out.println("filesArray: " + fileName);

			}
		}

		// ���� ÷������ ����
		FileDAO.getInstance().deleteFile(boardNo);
		
		if (bVo.getBoardNo() != 0) {
			for (FileVO fVo : fileList) {
				// ���� ÷������ ����
				FileDAO.getInstance().createFile(fVo, bVo.getBoardNo());
			}
		}

		new BoardListAction().execute(request, response);
	}
}