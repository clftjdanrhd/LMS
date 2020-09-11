package com.lms.dto;

public class KeyVO {
	
	private int keyNo;
	private String place;
	private int labListNo;
	
	public int getKeyNo() {
		return keyNo;
	}
	public void setKeyNo(int keyNo) {
		this.keyNo = keyNo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getLabListNo() {
		return labListNo;
	}
	public void setLabListNo(int labListNo) {
		this.labListNo = labListNo;
	}
	
	@Override
	public String toString() {
		return "KeyVO [keyNo=" + keyNo + ", place=" + place + ", labListNo=" + labListNo + "]";
	}
	
	
	

}
