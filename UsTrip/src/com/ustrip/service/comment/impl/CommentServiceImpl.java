package com.ustrip.service.comment.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.service.comment.CommentDao;
import com.ustrip.service.comment.CommentService;
import com.ustrip.service.domain.Comment;

@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	@Qualifier("commentDaoImpl")
	private CommentDao commentDao;
	public void setBoardDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public CommentServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}

	@Override
	public int addComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.addComment(comment);
	}

	@Override
	public List<Comment> listComment(HashMap boards) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.listComment(boards);
	}

	@Override
	public int updateComment(Comment comment) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.updateComment(comment);
	}

	@Override
	public int deleteComment(int commentNo) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.deleteComment(commentNo);
	}

	@Override
	public List<Comment> getComment(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.getComment(boardNo);
	}

	@Override
	public Comment oneComment(int commentNo) throws Exception {
		// TODO Auto-generated method stub
		return commentDao.oneComment(commentNo);
	}

}
