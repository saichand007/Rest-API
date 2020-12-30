package com.restservices.model;

public class Department {

	private Integer deptId;
	private String deptName;
	private Integer deptLocation;
	private String msg;
	private int code;
	
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Integer getDeptLocation() {
		return deptLocation;
	}
	public void setDeptLocation(Integer deptLocation) {
		this.deptLocation = deptLocation;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", deptLocation=" + deptLocation + ", msg="
				+ msg + ", code=" + code + "]";
	}
	public Department(Integer deptId, String deptName, Integer deptLocation, String msg, int code) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.deptLocation = deptLocation;
		this.msg = msg;
		this.code = code;
	}
	public Department() {

	}
	
	
	
}
