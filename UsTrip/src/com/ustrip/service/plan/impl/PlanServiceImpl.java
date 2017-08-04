package com.ustrip.service.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ustrip.common.Search;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Message;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;
import com.ustrip.service.plan.PlanDAO;
import com.ustrip.service.plan.PlanService;

@Service("planServiceImpl")
public class PlanServiceImpl implements PlanService{

	@Autowired
	@Qualifier("planDAOImpl")
	private PlanDAO planDAO;
	public void setPlanDao(PlanDAO planDAO){
		this.planDAO = planDAO;
	}
	
	@Override
	public List<Place> listPlace(int travelNo) throws Exception {
		return planDAO.listPlace(travelNo);
	}

	@Override
	public int checkBlogStart(int travelNo) throws Exception {
		return planDAO.checkBlogStart(travelNo);
	}

	@Override
	public void startBlog(int travelNo) throws Exception {
		planDAO.startBlog(travelNo);
	}

	@Override
	public List<Integer> listPlaceNo(Search search) {
		return planDAO.listPlaceNo(search);
	}

	@Override
	public List<Integer> listPlaceNoTemp(int travelNo) throws Exception {
		return planDAO.listPlaceNoTemp(travelNo);
	}
	
	public PlanServiceImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addTravel(Travel travel) throws Exception {
		planDAO.addTravel(travel);
	}
	
	public void addCity(City city) throws Exception{
		planDAO.addCity(city);
	}
	
	public void addPlace(Place place) throws Exception{
		planDAO.addPlace(place);
	}
	
	public Travel getTravel(int travNo) throws Exception {
		return planDAO.getTravel(travNo);
	}
	
	public Travel getTravel(Travel travel) throws Exception {
		return planDAO.getTravel(travel);
	}
	
	public List<City> getCity(int travNo) throws Exception{
		return planDAO.getCity(travNo);
	}
	
	public Object getCityNo(String tNo ,String cId) throws Exception{
		return planDAO.getCityNo(tNo, cId);
	}
	
	public Place getPlace(Place place) throws Exception{
		return planDAO.getPlace(place);
	}
	
	
	public void addPlan(Travel travel, City city, Place place) throws Exception {
		planDAO.addTravel(travel);
		planDAO.addCity(city);
		planDAO.addPlace(place);
	}
	
	public Map<String, Object> getListTravel(Search search) throws Exception {
		List<Travel> list = planDAO.getListTravel(search);
//		System.out.println("listtttttttttttttt ::" + list);
	
		int totalCount = planDAO.totalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	@Override
	public List<City> blogCity(int travNo) throws Exception {
		// TODO Auto-generated method stub
		return planDAO.blogCity(travNo);
	}

	@Override
	public List<Place> blogPlace(int cityNo) throws Exception {
		// TODO Auto-generated method stub
		return planDAO.blogPlace(cityNo);
	}

	@Override
	public void updateThumbnail(Travel travel) throws Exception {
		// TODO Auto-generated method stub
		planDAO.updateThumbnail(travel);
	}

	
}