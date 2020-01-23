package com.lms.dto;

import java.util.Date;

public class ReplyVO {
	
	private int replyNo;
	private Date regDate;
	private Date updateDate;
	private int boardNo;
	private int userNo;
	private String replyText;
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", regDate=" + regDate + ", updateDate=" + updateDate + ", boardNo="
				+ boardNo + ", userNo=" + userNo + ", replyText=" + replyText + "]";
	}

	
	
	
}
