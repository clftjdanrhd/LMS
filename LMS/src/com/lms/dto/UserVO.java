package com.lms.dto;

public class UserVO {
	
	
	private String userno;
	private String userpw;
	private String username;
	private int usertel;
	private String state;
	private String authority;
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUsertel() {
		return usertel;
	}
	public void setUsertel(int usertel) {
		this.usertel = usertel;
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
		return "UserVO [userno=" + userno + ", userpw=" + userpw + ", username=" + username + ", usertel=" + usertel
				+ ", state=" + state + ", authority=" + authority + "]";
	}


}
