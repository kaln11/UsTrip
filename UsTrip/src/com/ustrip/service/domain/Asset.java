package com.ustrip.service.domain;

import java.util.Date;

public class Asset {
	
	private int assetNo;
	private int travNo;
	private int blogNo;
	private Date visitDate;
	private String usage;
	private String assetCategory;
	private int charge;
	private String travTitle;
	private Date startDate;
	private Date endDate;
	private int totalDate;
	private Date regDate;
	
	public Asset(){		
	}

	public int getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(int assetNo) {
		this.assetNo = assetNo;
	}

	public int getTravNo() {
		return travNo;
	}

	public void setTravNo(int travNo) {
		this.travNo = travNo;
	}

	public int getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}
		
	public String getTravTitle() {
		return travTitle;
	}

	public void setTravTitle(String travTitle) {
		this.travTitle = travTitle;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getTotalDate() {
		return totalDate;
	}

	public void setTotalDate(int totalDate) {
		this.totalDate = totalDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "AssetVo = [assetNo : "+assetNo+", travNo : "+travNo+", blogNo : "+blogNo+", visitDate : "+visitDate+", usage : "+usage+", assetCategory : "+assetCategory+", charge : "+charge+" ]";
	}
}
