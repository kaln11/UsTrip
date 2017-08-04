package com.ustrip.service.domain;

import java.util.Date;
import java.util.List;

public class Board {
	
	private int boardNo;
	private String nickName;
	private int travNo;
	private int hits;
	private String boardContent;
	private String boardTitle;
	private Date regDate;
	private int deleteFlag;
	private int boardCategory;
	private int countComment;
	private int numbering;
	
	public Board(){}

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

	public int getTravNo() {
		return travNo;
	}

	public void setTravNo(int travNo) {
		this.travNo = travNo;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getBoardCategory() {
		return boardCategory;
	}

	public void setBoardCategory(int boardCategory) {
		this.boardCategory = boardCategory;
	}
	
	public int getCountComment() {
		return countComment;
	}

	public void setCountComment(int countComment) {
		this.countComment = countComment;
	}

	public int getNumbering() {
		return numbering;
	}

	public void setNumbering(int numbering) {
		this.numbering = numbering;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "boardVO = [ boardNo : "+boardNo+", nickName : "+nickName+", travNo : "+travNo+", hits : "+hits+", boardContent : "+boardContent+", boardTitle : "+boardTitle+", regDate : "+regDate+", boardCategory : "+boardCategory+"countComment : "+countComment+" ]";
	}
	
}
