package com.ustrip.service.domain;

import java.util.Date;

public class Comment {
	
	private int commentNo;
	private int boardNo;
	private String nickName;
	private int privateComment;
	private Date regDate;
	private String commentContent;
	private String profile;
	
	public Comment(){}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getPrivateComment() {
		return privateComment;
	}

	public void setPrivateComment(int privateComment) {
		this.privateComment = privateComment;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "CommentVo = [ commentNo : "+commentNo+", boardNo : "+boardNo+", nickName : "+nickName+", privateComment : "+privateComment+", regDate : "+regDate+", commentContent : "+commentContent+" ]";
	}

}
