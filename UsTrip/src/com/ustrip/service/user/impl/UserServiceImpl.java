package com.ustrip.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Follow;
import com.ustrip.service.domain.User;
import com.ustrip.service.user.UserDao;
import com.ustrip.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {
	
	///Field
	@Autowired
	@Qualifier("userDaoImpl")	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}
	
	public void addUser(User user) throws Exception{
		userDao.addUser(user);
	}
	
	//타계정로그인 후 회원가입
	public void extraUserInfo(User user) throws Exception{
		userDao.extraUserInfo(user);
	}
	
	public boolean checkUserId(String userId) throws Exception{
		
		boolean result = true;
		
		User user = userDao.getUser(userId);
		
		if(user != null ) {	//userId가 존재한다면 false
			result = false;
		}
		return result;
	}
	
	public boolean checkNickName(String nickName) throws Exception{
		
		boolean result = true;
		
		User user = userDao.checkNickName(nickName);
		
		if(user != null ) {
			result = false;
		}
		return result;				
	}

	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
	
	public User getUserNickName(String nickName) throws Exception{
		return userDao.checkNickName(nickName);
	}
	
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}
	
	public Map<String, Object> listUser(Search search) throws Exception{
		List<User> list = userDao.listUser(search);
		int totalCount  = userDao.getUserTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}
	
	//회원탈퇴
	public void withdrawUser(String userId) throws Exception{
		userDao.withdrawUser(userId);
	}
	
	public String getRandomPassword(int length) throws Exception { 
		String uuid="";
		
		for (int i = 0;i < length; i++) { 
			 uuid = UUID.randomUUID().toString().replaceAll("-", "");
			 uuid = uuid.substring(0, 10);
		     System.out.println(i + ") " +uuid); 
		 } 
		 
		 System.out.println("uuid :: " + uuid);

		 return uuid;
	}

	public void addFollow(String targetUserId, String folUserId) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("targetUserId", targetUserId);
		map.put("folUserId", folUserId);
		
		String temp = targetUserId;
		targetUserId = folUserId;
		folUserId = temp;
		
		Follow follow = userDao.getFollow(folUserId, targetUserId);
		
		if( follow != null ) {
			System.out.println("map!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! " + map);
			
			follow.setIsFollowing(1);
			
			userDao.updateFollow(follow);
			
			System.out.println("follow 뭐닝 :: " + follow);
			
			int isFollowing = 1;
			
			map.put("isFollowing", isFollowing);
			
			System.out.println("mapppppppppppppppp :::::: " +  map);
			
			userDao.addFollow(map);
		} else {
		userDao.addFollow(map);
		}
	}
	
	public Map<String, Object> listFollow(Search search, String folUserId) throws Exception{
		
		search.setSearchKeyword(folUserId);
		
		List<Follow> list = userDao.listFollow(search);
		int totalCount  = userDao.getFollowTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}
	
	public Follow getFollow(String folUserId, String targetUserId ) throws Exception{
		return userDao.getFollow(folUserId, targetUserId);
	}
	
	public Map<String, Object> listFollowing(Search search, String targetUserId) throws Exception{
		
		search.setSearchKeyword(targetUserId);
		
		List<Follow> list = userDao.listFollowing(search);
		int totalCount  = userDao.getFollowingTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", totalCount);
		
		return map;
	}
	
	public void deleteFollow(String folUserId, String targetUserId ) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("targetUserId", targetUserId);
		map.put("folUserId", folUserId);
		
		String temp = targetUserId;
		targetUserId = folUserId;
		folUserId = temp;
		
		System.out.println("map에 담긴게 무엇이냐 :: " + map);
		
		Follow follow = userDao.getFollow(folUserId, targetUserId);
		
		if( follow != null) {
			
			follow.setIsFollowing(0);
			
			userDao.updateFollow(follow);
			System.out.println("follow delete??????????????????? :: " +follow);
			
		}
		userDao.deleteFollow(map);
	/*	userDao.deleteFollow(folUserId, targetUserId);*/
	}

	@Override
	public String getUserId(String nickName) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserId(nickName);
	}

}
