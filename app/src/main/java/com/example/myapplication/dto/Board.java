package com.example.myapplication.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Board implements Serializable {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bdate;
	private String mid;
	private int bhitcount;
	private String battachoname;
	private String battachtype;
	private byte[] battachdata;

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBdate() {
		return bdate;
	}

	public void setBdate(String bdate) {
		this.bdate = bdate;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public int getBhitcount() {
		return bhitcount;
	}

	public void setBhitcount(int bhitcount) {
		this.bhitcount = bhitcount;
	}

	public String getBattachoname() {
		return battachoname;
	}

	public void setBattachoname(String battachoname) {
		this.battachoname = battachoname;
	}

	public String getBattachtype() {
		return battachtype;
	}

	public void setBattachtype(String battachtype) {
		this.battachtype = battachtype;
	}

	public byte[] getBattachdata() {
		return battachdata;
	}

	public void setBattachdata(byte[] battachdata) {
		this.battachdata = battachdata;
	}

	@Override
	public String toString() {
		return "Board{" +
				"bno=" + bno +
				", btitle='" + btitle + '\'' +
				", bcontent='" + bcontent + '\'' +
				", bdate='" + bdate + '\'' +
				", mid='" + mid + '\'' +
				", bhitcount=" + bhitcount +
				", battachoname='" + battachoname + '\'' +
				", battachtype='" + battachtype + '\'' +
				", battachdata=" + Arrays.toString(battachdata) +
				'}';
	}
}
