package com.lms.dto;

public class UserVO {
	
	
	private int userNo;
	private String userPw;
	private String userName;
	private int userTel;
	private String state;
	private String authority;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserTel() {
		return userTel;
	}
	public void setUserTel(int userTel) {
		this.userTel = userTel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userPw=" + userPw + ", userName=" + userName + ", userTel=" + userTel
				+ ", state=" + state + ", authority=" + authority + "]";
	}
	
	
	
	
	

	
	

}
