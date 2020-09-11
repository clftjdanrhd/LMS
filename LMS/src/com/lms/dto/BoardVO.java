package com.lms.dto;

import java.util.ArrayList;
import java.util.Date;

public class BoardVO{
	
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date regDate;
	private int viewCnt;
	ArrayList<FileVO> fileList = new ArrayList<FileVO>();
	private String userNo;
	private String userName;
	private String boardPass;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	public ArrayList<FileVO> getFileList() {
		return fileList;
	}
	public void setFileList(ArrayList<FileVO> fileList) {
		this.fileList = fileList;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", fileList=" + fileList + ", userNo=" + userNo
				+ ", userName=" + userName + ", boardPass=" + boardPass + "]";
	}


}
