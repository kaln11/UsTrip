package com.ustrip.service.domain;


public class HashTag {
	
	private int tagNo;
	private int blogNo;
	private String hashTag;
	
	public HashTag() {
	}
	
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public int getBlogNo() {
		return blogNo;
	}
	public void setBlogNo(int blogNo) {
		this.blogNo = blogNo;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	
	@Override
	public String toString() {
		return "HashTag [tagNo=" + tagNo + ", blogNo=" + blogNo + ", hashTag=" + hashTag + "]";
	}

}
