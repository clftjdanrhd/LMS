package com.lms.dto;

import java.util.Date;

public class NoticeVO {
   
   private int noticeNo;
   private String noticeTitle;
   private String noticeContent;
   private Date regDate;
   private int viewCnt;
   private String noticePass;
   private String userNo;
   private String userName;
public int getNoticeNo() {
	return noticeNo;
}
public void setNoticeNo(int noticeNo) {
	this.noticeNo = noticeNo;
}
public String getNoticeTitle() {
	return noticeTitle;
}
public void setNoticeTitle(String noticeTitle) {
	this.noticeTitle = noticeTitle;
}
public String getNoticeContent() {
	return noticeContent;
}
public void setNoticeContent(String noticeContent) {
	this.noticeContent = noticeContent;
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
public String getNoticePass() {
	return noticePass;
}
public void setNoticePass(String noticePass) {
	this.noticePass = noticePass;
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
@Override
public String toString() {
	return "NoticeVO [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
			+ ", regDate=" + regDate + ", viewCnt=" + viewCnt + ", noticePass=" + noticePass + ", userNo=" + userNo
			+ ", userName=" + userName + "]";
}


}