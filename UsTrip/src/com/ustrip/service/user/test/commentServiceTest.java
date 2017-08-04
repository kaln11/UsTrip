package com.ustrip.service.user.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ustrip.service.asset.AssetService;
import com.ustrip.service.board.BoardService;
import com.ustrip.service.comment.CommentService;
import com.ustrip.service.domain.Asset;
import com.ustrip.service.domain.Board;
import com.ustrip.service.domain.Comment;

/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class commentServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("commentServiceImpl")
	private CommentService commentdService;
	
	//@Test
	public void testfindpur() throws Exception {
		
		Comment comment = new Comment();
		
		comment.setBoardNo(70001);
		comment.setNickName("user001");;
		comment.setPrivateComment(0);
		comment.setCommentContent("왜러간다아아럳랴ㅑㅓ아");
		
		int check = commentdService.addComment(comment);

		Assert.assertEquals(1, check);
				
	}
	
	//@Test
	public void testupdate() throws Exception {		
		
		Comment comment = new Comment();
		
		comment.setCommentNo(80003);
		comment.setBoardNo(70002);
		comment.setNickName("user001");;
		comment.setPrivateComment(1);
		comment.setCommentContent("배고파ㅐ대쟈더랮댜ㅓ래");
		
		int check = commentdService.updateComment(comment);

		Assert.assertEquals(1, check);
				
	}
	
	@Test
	public void testdel() throws Exception {
		
		int check = commentdService.deleteComment(80024);
		Assert.assertEquals(1, check);
		
	}
	
	//@Test
	public void testget() throws Exception {
		
		HashMap hm = new HashMap();
		List<Integer> no = new ArrayList();
		String order = "DESC";
		no.add(70002);
		//no.add(70004);
		no.add(70001);
		hm.put("boards", no);
				
		List<Comment> comment = commentdService.listComment(hm);
		Assert.assertEquals(2, comment.size());
		
	}
	
	//@Test
		public void testget1() throws Exception {
			
			List<Comment> check = commentdService.getComment(70004);
			Assert.assertEquals(3, check.size());
			
		}
		
}
	
	//@Test
