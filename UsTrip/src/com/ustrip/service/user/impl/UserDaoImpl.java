package com.ustrip.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Follow;
import com.ustrip.service.domain.User;
import com.ustrip.service.user.UserDao;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void addUser(User user) throws Exception {
		sqlSession.insert("UserMapper.addUser", user);
	}
	
	public void extraUserInfo(User user) throws Exception{
		sqlSession.insert("UserMapper.extraUserInfo", user);
	}
	
	public User getUser(String userId) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	//´Ð³×ÀÓÁ¶È¸
	public User checkNickName(String nickName) throws Exception{
		return sqlSession.selectOne("UserMapper.checkNickName", nickName);
	}
	
	public void updateUser(User user) throws Exception {
		sqlSession.update("UserMapper.updateUser", user);
	}
	
	public List<User> listUser(Search search) throws Exception{
		return sqlSession.selectList("UserMapper.listUser", search);
	}
	
	public int getUserTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("UserMapper.getUserTotalCount", search);
	}
	//È¸¿øÅ»Åð
	public  void withdrawUser(String userId) throws Exception{
		sqlSession.update("UserMapper.withdrawUser", userId);
	}
	
	public void addFollow(Map<String, Object> map) throws Exception{
		System.out.println("map :::::::::::::::: " + map);
		sqlSession.insert("FollowMapper.addFollow", map);
	}
	
	public List<Follow> listFollow(Search search) throws Exception{
		return sqlSession.selectList("FollowMapper.listFollow", search);
	}
	
	public int getFollowTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("FollowMapper.getFollowTotalCount", search);
	}
	
	public Follow getFollow(String folUserId, String targetUserId) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("folUserId", folUserId);
		map.put("targetUserId", targetUserId);
		
		System.out.println("##### " + map.get("folUserId"));
		
		return sqlSession.selectOne("FollowMapper.getFollow",map);
	}
	
	public void updateFollow(Follow follow) throws Exception {
		sqlSession.update("FollowMapper.updateFollow", follow);
	}
	
	public List<Follow> listFollowing(Search search) throws Exception{
		return sqlSession.selectList("FollowMapper.listFollowing", search);
	}
	
	public List<Follow> isFollowing(Search search) throws Exception{
		return sqlSession.selectList("FollowMapper.isFollowing", search);
	}
	
	public int getFollowingTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("FollowMapper.getFollowingTotalCount", search);
	}
	
	public void deleteFollow(Map<String, Object> map) throws Exception{
		sqlSession.selectOne("FollowMapper.deleteFollow",map);
	}

	@Override
	public String getUserId(String nickName) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("UserMapper.getUserId", nickName);
	}

}
