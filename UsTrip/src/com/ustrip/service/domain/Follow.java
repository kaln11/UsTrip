package com.ustrip.service.domain;

public class Follow extends User{
	
	//Field
	private int followNo;
	private String folUserId;
	private String targetUserId;
	private int isFollowing;		//맞팔여부 '0'인경우 X / '1'인경우 맞팔

	//Controctor
	public Follow() {
	}

	public int getFollowNo() {
		return followNo;
	}

	public String getFolUserId() {
		return folUserId;
	}

	public String getTargetUserId() {
		return targetUserId;
	}

	public void setFollowNo(int followNo) {
		this.followNo = followNo;
	}

	public void setFolUserId(String folUserId) {
		this.folUserId = folUserId;
	}

	public void setTargetUserId(String targetUserId) {
		this.targetUserId = targetUserId;
	}

	public int getIsFollowing() {
		return isFollowing;
	}

	public void setIsFollowing(int isFollowing) {
		this.isFollowing = isFollowing;
	}

	@Override
	public String toString() {
		return "Follow [followNo=" + followNo + ", folUserId=" + folUserId + ", targetUserId=" + targetUserId
				+ ", isFollowing=" + isFollowing + "]";
	}

}
