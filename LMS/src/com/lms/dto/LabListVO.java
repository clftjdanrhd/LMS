package com.lms.dto;

import java.util.Date;

public class LabListVO {
	
	private int labListNo;
	private Date starTime;
	private Date endTime;
	private String authority;
	private String returnPl;
	private int labNo;
	private int userNo;
	public int getLabListNo() {
		return labListNo;
	}
	public void setLabListNo(int labListNo) {
		this.labListNo = labListNo;
	}
	public Date getStarTime() {
		return starTime;
	}
	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getReturnPl() {
		return returnPl;
	}
	public void setReturnPl(String returnPl) {
		this.returnPl = returnPl;
	}
	public int getLabNo() {
		return labNo;
	}
	public void setLabNo(int labNo) {
		this.labNo = labNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	@Override
	public String toString() {
		return "LabListVO [labListNo=" + labListNo + ", starTime=" + starTime + ", endTime=" + endTime + ", authority="
				+ authority + ", returnPl=" + returnPl + ", labNo=" + labNo + ", userNo=" + userNo + "]";
	}
	
	
	
	
	

}
