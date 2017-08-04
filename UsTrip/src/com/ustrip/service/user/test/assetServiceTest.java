package com.ustrip.service.user.test;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ustrip.service.asset.AssetService;
import com.ustrip.service.domain.Asset;

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
public class assetServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("assetServiceImpl")
	private AssetService assetService;
	
	@Test
	public void testfindpur() throws Exception {
		
		Asset asset = new Asset();
		SimpleDateFormat format = new SimpleDateFormat("yyyy/mm/dd");
		Date visit = format.parse("1970/01/02");
		
		asset.setTravNo(4);
		asset.setBlogNo(30001);
		asset.setVisitDate(visit);
		asset.setUsage("교통짬뽕");
		asset.setAssetCategory("식비");
		asset.setCharge(6500);
		
		/*int check = assetService.addAsset(asset);*/

		/*Assert.assertEquals(1, check);*/
				
	}
	
	//@Test
	public void testUpdate()throws Exception{
		Asset asset = new Asset();
		Date visit = new Date(90/10/10);
		
		asset.setAssetNo(60043);
		asset.setTravNo(4);
		asset.setBlogNo(30001);
		asset.setVisitDate(visit);
		asset.setUsage("성심당");
		asset.setAssetCategory("식비");
		asset.setCharge(4500);
		
		int check = assetService.updateAsset(asset);

		Assert.assertEquals(1, check);
	}
	
}
	
	//@Test
