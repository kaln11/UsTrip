package com.ustrip.service.user.test;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ustrip.common.Page;
import com.ustrip.common.Search;
import com.ustrip.service.asset.AssetService;
import com.ustrip.service.board.BoardService;
import com.ustrip.service.domain.Asset;
import com.ustrip.service.domain.Board;

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
public class boardServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("boardServiceImpl")
	private BoardService boardService;
	
	@Test
	public void testfindpur() throws Exception {
		
		Board board = new Board();
		
		board.setTravNo(19);
		board.setNickName("peterchoi");
		board.setHits(0);
		board.setBoardContent("가나다라마바사아어ㅐ대쟈더랮댜ㅓ래");
		board.setBoardTitle("맛집 여행을 계획중입니다. ");
		board.setBoardCategory(0);
		
		int check = boardService.addBoard(board);

		Assert.assertEquals(1, check);
				
	}
	
	//@Test
	public void testupdate() throws Exception {
		
		Board board = new Board();
		
		board.setBoardNo(70002);
		board.setTravNo(4);
		board.setNickName("user001");;
		board.setHits(0);;
		board.setBoardContent("가나다라마바사아어ㅐ대쟈더랮댜ㅓ래");
		board.setBoardTitle("여행제목");
		board.setBoardCategory(0);
		
		int check = boardService.updateBoard(board);

		Assert.assertEquals(1, check);
				
	}
	
	//@Test
	public void testdel() throws Exception {
		
		int check = boardService.deleteBoard(70003);

		Assert.assertEquals(1, check);
		
	}
	
	//@Test
	public void testget() throws Exception {
		
		Board check = boardService.getBoard(70002);

		Assert.assertEquals("여행제목", check.getBoardTitle());
		
	}
	
	//@Test
	public void testlist() throws Exception {
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setOrder("DESC");
		search.setBoardCategory(0);
		search.setPageSize(5);
		
		List<Board> map = boardService.listBoard(search);
				
		Assert.assertEquals(3, map );
		
	}
	
}
	
	//@Test
