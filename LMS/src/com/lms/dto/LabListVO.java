package com.lms.dto;

import java.util.Date;

public class LabListVO {
	
	private int labListNo;
	private String startTime;
	private String endTime;
	private String authority;
	private String userNo;
	private String state;
	private String userName;
	public int getLabListNo() {
		return labListNo;
	}
	public void setLabListNo(int labListNo) {
		this.labListNo = labListNo;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "LabListVO [labListNo=" + labListNo + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", authority=" + authority + ", userNo=" + userNo + ", state=" + state + ", userName=" + userName
				+ "]";
	}
	
	
	
	
	
}