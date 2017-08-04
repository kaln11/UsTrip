package com.ustrip.service.domain;

public class LikeTravel {
	
	private int likeTravNo;
	private String userId;
	private Travel travNo;

	public LikeTravel() {
		// TODO Auto-generated constructor stub
	}
	
	public int getLikeTravNo() {
		return likeTravNo;
	}
	public void setLikeTravNo(int likeTravNo) {
		this.likeTravNo = likeTravNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Travel getTravNo() {
		return travNo;
	}
	public void setTravNo(Travel travNo) {
		this.travNo = travNo;
	}

	@Override
	public String toString() {
		return "LikeTravel [likeTravNo=" + likeTravNo + ", userId=" + userId + ", travNo=" + travNo + "]";
	}
	
}
