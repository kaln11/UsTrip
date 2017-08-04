package com.ustrip.service.comment;

import java.util.HashMap;
import java.util.List;

import com.ustrip.service.domain.Comment;

public interface CommentDao {
	
	public int addComment(Comment comment)throws Exception;
	
	public List<Comment> listComment(HashMap boards)throws Exception;
	
	public int updateComment(Comment comment)throws Exception;
	
	public int deleteComment(int commentNo)throws Exception;
	
	public List<Comment> getComment(int boardNo)throws Exception;
	
	public Comment oneComment(int commentNo)throws Exception;

}
