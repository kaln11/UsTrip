package com.ustrip.service.message.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Message;
import com.ustrip.service.message.MessageDao;

@Repository("messageDaoImpl")
public class MessageDaoImpl implements MessageDao{

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public MessageDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void sendMsg(Message message) throws Exception {
		sqlSession.insert("MessageMapper.sendMsg", message);
	}
	
	public Message getMsg(int msgNo) throws Exception {
		return sqlSession.selectOne("MessageMapper.getMsg", msgNo);
	}
	
	public void updateIsRead(int msgNo) throws Exception{
		sqlSession.update("MessageMapper.updateIsRead", msgNo);
	}
	
	public void deleteSendMsg(int msgNo) throws Exception {
		sqlSession.update("MessageMapper.updateSendDel", msgNo);
	}
	
	public void deleteReceiveMsg(int msgNo) throws Exception {
		sqlSession.update("MessageMapper.updateReceiveDel", msgNo);
	}
	
	public List<Message> listSendMsg(Search search) throws Exception{
		return sqlSession.selectList("MessageMapper.listSendMsg", search);
	}
	
	public int sendCount(Search search) throws Exception {
		return sqlSession.selectOne("MessageMapper.sendCount", search);
	}
	
	public List<Message> listReceivMsg(Search search) throws Exception {
		return sqlSession.selectList("MessageMapper.listReceivMsg", search);		
	}
	
	public int receiveCount(Search search) throws Exception {
		return sqlSession.selectOne("MessageMapper.receiveCount", search);
	}
	
	public int isReadMsg(String receiver) throws Exception {
		return sqlSession.selectOne("MessageMapper.isReadMsg", receiver);
	}
	
}
