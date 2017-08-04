package com.ustrip.service.message;

import java.util.List;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Message;

public interface MessageDao {
	
	//메세지보내기
	public void sendMsg(Message message) throws Exception;
	
	public Message getMsg(int msgNo) throws Exception;
	
	public void updateIsRead(int msgNo) throws Exception;
	
	public void deleteSendMsg(int msgNo) throws Exception;
	
	public void deleteReceiveMsg(int msgNo) throws Exception;

	//보낸쪽지함리스트
	public List<Message> listSendMsg(Search search) throws Exception;
	
	public int sendCount(Search search) throws Exception;
	
	//받은쪽지함리스트
	public List<Message> listReceivMsg(Search search) throws Exception;
	
	public int receiveCount(Search search) throws Exception;	
	
	public int isReadMsg(String receiver) throws Exception;
	
}
