package com.ustrip.service.domain;

import java.sql.Date;

public class Message {
	
	//Field
	private int msgNo;
	private String sender;
	private String receiver;
	private Date sendDate;
	private Date readDate;
	private String msgContent;
	private int isRead;
	private int sendDel;
	private int receiveDel;	
	
	//Contructor
	public Message() {
	}

	//Method
	public int getMsgNo() {
		return msgNo;
	}

	public String getSender() {
		return sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public Date getReadDate() {
		return readDate;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public int getSendDel() {
		return sendDel;
	}

	public int getReceiveDel() {
		return receiveDel;
	}

	public void setSendDel(int sendDel) {
		this.sendDel = sendDel;
	}

	public void setReceiveDel(int receiveDel) {
		this.receiveDel = receiveDel;
	}

	@Override
	public String toString() {
		return "Message [msgNo=" + msgNo + ", sender=" + sender + ", receiver=" + receiver + ", sendDate=" + sendDate
				+ ", readDate=" + readDate + ", msgContent=" + msgContent + ", isRead=" + isRead + ", sendDel="
				+ sendDel + ", receiveDel=" + receiveDel + "]";
	}
	
}
