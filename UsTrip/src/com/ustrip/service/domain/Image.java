package com.ustrip.service.domain;

public class Image {
	
	private int imgNo;
	private int blogNo;
	private String originalName;
	private String serverImgName;
	private int travNo;
	
	public Image() {
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public int getBlogNo() {
		return blogNo;
	}

	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}

	public String getServerImgName() {
		return serverImgName;
	}

	public void setServerImgName(String serverImgName) {
		this.serverImgName = serverImgName;
	}

	public int getTravNo() {
		return travNo;
	}

	public void setTravNo(int travNo) {
		this.travNo = travNo;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	@Override
	public String toString() {
		return "Image [imgNo=" + imgNo + ", blogNo=" + blogNo + ", serverImgName=" + serverImgName +"originName : "+originalName+", travNo : "+travNo+ "]";
	}
	
}
