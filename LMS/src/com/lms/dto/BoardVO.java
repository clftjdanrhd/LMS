package com.lms.dto;

import java.util.ArrayList;
import java.util.Date;

public class BoardVO {
	
	
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private Date regDate;
	private int viewCnt;
	ArrayList<String> Filelist = new ArrayList<String>();
	private int userNo;
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
	public ArrayList<String> getFilelist() {
		return Filelist;
	}
	public void setFilelist(ArrayList<String> filelist) {
		Filelist = filelist;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", Filelist=" + Filelist + ", userNo=" + userNo
				+ ", boardPass=" + boardPass + "]";
	}
	
	
	

}
