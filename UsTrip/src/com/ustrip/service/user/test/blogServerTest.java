package com.ustrip.service.user.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ustrip.service.blog.BlogService;
import com.ustrip.service.board.BoardService;
import com.ustrip.service.domain.Asset;
import com.ustrip.service.domain.Board;
import com.ustrip.service.domain.Image;

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
public class blogServerTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("blogServiceImpl")
	private BlogService blogService;
	
	@Test
	public void testfindpur() throws Exception {
		List<Image> images = new ArrayList();
		Image image = new Image();
		
		image.setTravNo(4);
		image.setBlogNo(30001);
		image.setOriginalName("0280020001122.jpg");
		image.setServerImgName("7ac19098-6c1a-465b-8969-843a2538b5da.jpg");
		images.add(image);
		
		 image = new Image();
			
			image.setTravNo(4);
			image.setBlogNo(30001);
			image.setOriginalName("AdventureTime_2304x1296_hperticarati.jpg");
			image.setServerImgName("8ff20ed4-e396-42be-8189-4de09c8e3957.jpg");
			images.add(image);
		
		System.out.println(images);
		int check = blogService.addImage(images);

		Assert.assertEquals(2, check);
				
	}
	
	
	
}
	
	//@Test
