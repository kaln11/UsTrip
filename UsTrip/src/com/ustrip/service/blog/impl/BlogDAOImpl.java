package com.ustrip.service.blog.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.common.Search;
import com.ustrip.service.blog.BlogDAO;
import com.ustrip.service.domain.Blog;
import com.ustrip.service.domain.HashTag;
import com.ustrip.service.domain.Image;
import com.ustrip.service.domain.LikeTravel;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;



@Repository("blogDAOImpl")
public class BlogDAOImpl implements BlogDAO{
	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public BlogDAOImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addBlog(List<Place> tempBlog) throws Exception {
		sqlSession.selectList("BlogMapper.addBlog", tempBlog);
	}

	@Override
	public int addJsonTag(HashTag hashtag) throws Exception {
		return sqlSession.insert("BlogMapper.addHashTag", hashtag);
	}

	@Override
	public Blog getJsonBlog(int blogNo) throws Exception {
		return sqlSession.selectOne("BlogMapper.getBlog", blogNo);
	}

	@Override
	public int addImage(List<Image> images) throws Exception {
		return sqlSession.insert("BlogMapper.addImage", images);
	}
	
	@Override
	public void deleteBlog(int blogNo) throws Exception {
		sqlSession.update("BlogMapper.deleteBlog", blogNo);
	}

	@Override
	public List<Blog> listBlog(int travNo) throws Exception {
		return sqlSession.selectList("BlogMapper.listBlog", travNo);
	}

	@Override
	public void updateScore(Blog blog) throws Exception {
		sqlSession.update("BlogMapper.updateScore", blog);
	}

	@Override
	public void updateReview(Blog blog) throws Exception {
		sqlSession.update("BlogMapper.updateReview", blog);
	}

	@Override
	public void deleteTag(int tagNo) throws Exception {
		sqlSession.delete("BlogMapper.deleteHashTag", tagNo);
	}

	@Override
	public void deleteImage(int imgNo) throws Exception {
		sqlSession.delete("BlogMapper.deleteImage", imgNo);
	}

	@Override
	public List<Blog> listImgByBlogNo(List<Integer> blogNo) throws Exception {
		List<Blog> list=new ArrayList<Blog>();
		for(int i=0; i<blogNo.size(); i++){
			Blog blog=sqlSession.selectOne("BlogMapper.getBlog", blogNo.get(i));
			List<Image> temp=sqlSession.selectList("BlogMapper.listImage", blogNo.get(i));
			blog.setImages(temp);
			list.add(blog);
		}
		return list;
	}

	@Override
	public List<Integer> listBlogNo(int travelNo) throws Exception {
		return sqlSession.selectList("BlogMapper.listBlogNo", travelNo);
	}

	@Override
	public List<Blog> listBlogImage(Search search) throws Exception {
		return sqlSession.selectList("BlogMapper.listBlogImage", search);
	}

	@Override
	public List<LikeTravel> checkLikeTravel(int travNo) throws Exception {		
		
		return sqlSession.selectList("BlogMapper.checkLikeTravel", travNo);			
	}

	@Override
	public List<LikeTravel> listLikeTravel(String userId) throws Exception {
		return sqlSession.selectList("BlogMapper.listLikeTravel", userId);
	}

	@Override
	public void deleteJsonLike(int travNo, String userId) throws Exception {
		Search search=new Search();
		search.setSearchKeyword(userId);
		search.setSearchCondition(Integer.toString(travNo));
		sqlSession.delete("BlogMapper.deleteLike", search);
	}

	@Override
	public void addJsonLike(int travNo, String userId) throws Exception {
		Search search=new Search();
		search.setSearchKeyword(userId);
		search.setSearchCondition(Integer.toString(travNo));
		sqlSession.delete("BlogMapper.addLike", search);
	}
	//좋아요한 여행
	@Override
	public List<LikeTravel> getListLikeTravel(Search search) throws Exception {
		return sqlSession.selectList("BlogMapper.getListLikeTravel", search);
	}
}
