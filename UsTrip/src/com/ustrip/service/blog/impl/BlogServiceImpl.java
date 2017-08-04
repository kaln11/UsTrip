package com.ustrip.service.blog.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.common.Search;
import com.ustrip.service.blog.BlogDAO;
import com.ustrip.service.blog.BlogService;
import com.ustrip.service.domain.Blog;
import com.ustrip.service.domain.HashTag;
import com.ustrip.service.domain.Image;
import com.ustrip.service.domain.LikeTravel;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;


@Service("blogServiceImpl")
public class BlogServiceImpl implements BlogService{
	
	@Autowired
	@Qualifier("blogDAOImpl")
	private BlogDAO blogDAO;
	
	public BlogServiceImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public void addBlog(List<Place> tempBlog) throws Exception {
		blogDAO.addBlog(tempBlog);
	}

	@Override
	public void addJsonTag(HashTag hashtag) throws Exception {
		blogDAO.addJsonTag(hashtag);
	}

	@Override
	public int addImage(List<Image> images) throws Exception {
		return blogDAO.addImage(images);
	}

	@Override
	public Blog getJsonBlog(int blogNo) throws Exception {
		return blogDAO.getJsonBlog(blogNo);
	}

	@Override
	public void deleteBlog(int blogNo) throws Exception {
		blogDAO.deleteBlog(blogNo);
	}

	@Override
	public List<Blog> listBlog(int travNo) throws Exception {
		return blogDAO.listBlog(travNo);
	}
	
	@Override
	public void updateScore(Blog blog) throws Exception {
		blogDAO.updateScore(blog);
	}

	@Override
	public void updateJsonReview(Blog blog) throws Exception {
		blogDAO.updateReview(blog);
	}

	@Override
	public void deleteTag(int tagNo) throws Exception {
		blogDAO.deleteTag(tagNo);
	}
	@Override
	public void deleteJsonImage(int imgNo) throws Exception {
		blogDAO.deleteImage(imgNo);
	}
	
	@Override
	public List<Blog> listImgByBlogNo(List<Integer> blogNo) throws Exception {
		return blogDAO.listImgByBlogNo(blogNo);
	}

	@Override
	public List<Integer> listBlogNo(int travelNo) throws Exception {
		return blogDAO.listBlogNo(travelNo);
	}

	@Override
	public List<Blog> listBlogImage(Search search) throws Exception {
		return blogDAO.listBlogImage(search);
	}

	@Override
	public List<LikeTravel> checkLikeTravel(int travelNo) throws Exception {
		return blogDAO.checkLikeTravel(travelNo);
	}

	@Override
	public List<LikeTravel> listLikeTravel(String userId) throws Exception {
		return blogDAO.listLikeTravel(userId);
	}

	@Override
	public void deleteJsonLike(int travNo, String userId) throws Exception {
		blogDAO.deleteJsonLike(travNo, userId);
	}

	@Override
	public void addJsonLike(int travNo, String userId) throws Exception {
		blogDAO.addJsonLike(travNo, userId);
	}
	
	public List<LikeTravel> getListLikeTravel(Search search) throws Exception {
		return blogDAO.getListLikeTravel(search);
	}

}
