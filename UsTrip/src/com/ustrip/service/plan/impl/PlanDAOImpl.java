package com.ustrip.service.plan.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ustrip.common.Search;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;
import com.ustrip.service.plan.PlanDAO;


@Repository("planDAOImpl")
public class PlanDAOImpl implements PlanDAO {
	
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	public PlanDAOImpl() {
		System.out.println(this.getClass());
	}
	
	@Override
	public List<Place> listPlace(int travNo) {
		List<Place> resultBlog = new ArrayList<Place>();
		List<City> places = sqlSession.selectList("CityMapper.listCity",travNo);
		
		for(City placeNos : places){			
			System.out.println(" city No : "+placeNos.getCityNo());
			List<Place> temp = sqlSession.selectList("PlaceMapper.listPlace",placeNos.getCityNo());
			for(Place tempBlog : temp){
				System.out.println("tempBlog : "+tempBlog);
				resultBlog.add(tempBlog);
			}
		}		
		return resultBlog;
	}

	@Override
	public int checkBlogStart(int travelNo) {
		return sqlSession.selectOne("TravelMapper.checkBlogStart", travelNo);
	}

	@Override
	public void startBlog(int travelNo) {
		sqlSession.update("TravelMapper.startBlog", travelNo);
	}
	
	@Override
	public List<Integer> listPlaceNo(Search search) {
		return sqlSession.selectList("PlaceMapper.listPlaceNo", search);
	}

	@Override
	public List<Integer> listPlaceNoTemp(int travelNo) {
		return sqlSession.selectList("PlaceMapper.listPlaceNoTemp", travelNo);
	}
	
	@Override
	public void addTravel(Travel travel) throws Exception {
		travel.setTravTitle(travel.getTravTitle()+"_"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(java.util.Calendar.getInstance().getTime()));
		sqlSession.insert("TravelMapper.addTravel",travel);
	}
	
	public void addCity(City city) throws Exception {
		city.setCity(city.getCity()+"_"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(java.util.Calendar.getInstance().getTime()));
		sqlSession.insert("CityMapper.addCity",city);
	}
	
	public void addPlace(Place place) throws Exception {
		sqlSession.insert("PlaceMapper.addPlace",place);
	}
	
	public Travel getTravel(int travNo) throws Exception {
		
		return sqlSession.selectOne("TravelMapper.getTravel",travNo);
	}
	
	public List<Travel> getListTravel(Search search) throws Exception {
		return sqlSession.selectList("TravelMapper.getListTravel", search);
	}
	
	public int totalCount(Search search) throws Exception{
		return sqlSession.selectOne("TravelMapper.totalCount", search);
	}
	
	public City getCity(City city) throws Exception {
		return sqlSession.selectOne("CityMapper.getCity",city.getTravelNo());
	}
	
	public Object getCityNo(String tNo ,String cId) throws Exception{
		
		City city = new City(); 
		city.setTravelNo(Integer.parseInt(tNo));
		city.setCityId(cId);
		
		return (Object) sqlSession.selectList("CityMapper.getCityNo", city);
	}

	public List<City> getCity(int travNo) throws Exception {
		return sqlSession.selectList("CityMapper.getCity", travNo);

	}
	
	public Place getPlace(Place place) throws Exception {
		return sqlSession.selectOne("PlaceMapper.getPlace",place.getPlaceNo());
	}
	@Override
	public List<City> blogCity(int travNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("CityMapper.listCity",travNo);
	}
	@Override
	public List<Place> blogPlace(int cityNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("PlaceMapper.listPlace",cityNo);
	}
	
public Travel getTravel(Travel travel) throws Exception {
		
		return sqlSession.selectOne("TravelMapper.getTravelTravel",travel.getTravTitle());
	}
@Override
public void updateThumbnail(Travel travel) throws Exception {
	// TODO Auto-generated method stub
	sqlSession.update("TravelMapper.thumbnailUpdate",travel);
}

}
