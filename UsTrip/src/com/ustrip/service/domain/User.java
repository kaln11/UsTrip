package com.ustrip.service.domain;

import java.sql.Date;

public class User{
	
	//Field
	private String userId;
	private String password;
	private String nickName;
	private String gender;
	private Date birthDate;
	private String profileImage;
	private int role;
	private int isDelete;
	
		
	//Contructor
	public User() {
	}


	public String getUserId() {
		return userId;
	}


	public String getPassword() {
		return password;
	}


	public String getNickName() {
		return nickName;
	}


	public String getGender() {
		return gender;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public String getProfileImage() {
		return profileImage;
	}


	public int getRole() {
		return role;
	}


	public int getIsDelete() {
		return isDelete;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public void setRole(int role) {
		this.role = role;
	}


	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", nickName=" + nickName + ", gender=" + gender
				+ ", birthDate=" + birthDate + ", profileImage=" + profileImage + ", role=" + role + ", isDelete="
				+ isDelete + "]";
	}

	
}
