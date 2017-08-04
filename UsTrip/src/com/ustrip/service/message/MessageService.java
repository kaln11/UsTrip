package com.ustrip.service.message;

import java.util.Map;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Message;

public interface MessageService {
	
	//메세지보내기
	public void sendMsg(Message message) throws Exception;
	
	public Message getMsg(int msgNo) throws Exception;
	
	public void updateIsRead(int msgNo) throws Exception;
	
	public void deleteMsg(int msgNo, String sessionId) throws Exception;

	public Map<String, Object> listSendMsg(Search search, String sender) throws Exception;
	
	public Map<String, Object> listReceivMsg(Search search, String receiver) throws Exception;
	
	public int isReadMsg(String receiver) throws Exception;
}
