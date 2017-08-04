package com.ustrip.service.comment.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.service.comment.CommentDao;
import com.ustrip.service.domain.Comment;

@Repository("commentDaoImpl")
public class CommentDaoImpl implements CommentDao{
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public CommentDaoImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public int addComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("CommentMapper.addComment",comment);		
	}

	@Override
	public List<Comment> listComment(HashMap boards) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CommentMapper.listComment", boards);
	}

	@Override
	public int updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update("CommentMapper.updateComment",comment);
	}

	@Override
	public int deleteComment(int commentNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete("CommentMapper.deleteComment",commentNo);
	}

	@Override
	public List<Comment> getComment(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CommentMapper.getComment", boardNo);
	}

	@Override
	public Comment oneComment(int commentNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("CommentMapper.oneComment", commentNo);
	}

}
