package com.example.myapplication.dto;

public class Member {
	private String mid;
	private String mname;
	private String mpassword;
	private boolean menabled;
	private String mrole;
	private String memail;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMpassword() {
		return mpassword;
	}

	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}

	public boolean isMenabled() {
		return menabled;
	}

	public void setMenabled(boolean menabled) {
		this.menabled = menabled;
	}

	public String getMrole() {
		return mrole;
	}

	public void setMrole(String mrole) {
		this.mrole = mrole;
	}

	public String getMemail() {
		return memail;
	}

	public void setMemail(String memail) {
		this.memail = memail;
	}

	@Override
	public String toString() {
		return "Member{" +
				"mid='" + mid + '\'' +
				", mname='" + mname + '\'' +
				", mpassword='" + mpassword + '\'' +
				", menabled=" + menabled +
				", mrole='" + mrole + '\'' +
				", memail='" + memail + '\'' +
				'}';
	}
}
