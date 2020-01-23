package com.lms.dto;

public class LabVO {
	
	private int labNo;
	private String location;
	private String equipment;
	public int getLabNo() {
		return labNo;
	}
	public void setLabNo(int labNo) {
		this.labNo = labNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	@Override
	public String toString() {
		return "LabVO [labNo=" + labNo + ", location=" + location + ", equipment=" + equipment + "]";
	}


}
