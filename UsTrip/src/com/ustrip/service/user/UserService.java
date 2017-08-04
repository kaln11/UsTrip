package com.ustrip.service.user;

import java.util.Map;

import com.ustrip.common.Search;
import com.ustrip.service.domain.Follow;
import com.ustrip.service.domain.User;

public interface UserService {
	
	//회원가입
	public void addUser(User user) throws Exception;
	
	//타계정로그인 후 회원가입
	public void extraUserInfo(User user) throws Exception;
	
	//회원ID 중복확인
	public boolean checkUserId(String userId) throws Exception;
	
	//회원닉네임 중복확인
	public boolean checkNickName(String nickName) throws Exception;	
	
	// 내정보확인 / 로그인
	public User getUser(String userId) throws Exception;
	
	//닉네임으로 정보조회
	public User getUserNickName(String nickName) throws Exception;
	
	// 내정보수정
	public void updateUser(User user) throws Exception;
		
	// 모든회원리스트
	public Map<String, Object> listUser(Search search) throws Exception;
	
	//회원탈퇴
	public void withdrawUser(String userId) throws Exception;
	
	public String getRandomPassword(int length) throws Exception;
	
	public void addFollow(String targetUserId, String sessionId) throws Exception;
	
	public Map<String, Object> listFollow(Search search, String folUserId) throws Exception;
	
	public Follow getFollow(String folUserId, String targetUserId) throws Exception;	
	
	public Map<String, Object> listFollowing(Search search, String targetUserId) throws Exception;

	public void deleteFollow(String folUserId, String targetUserId ) throws Exception;
	
	public String getUserId(String nickName) throws Exception;
	
}
