package com.ustrip.service.plan;

import java.util.List;
import java.util.Map;

import com.ustrip.common.Search;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;

public interface PlanService {

	public List<Place> listPlace(int travelNo) throws Exception;
	
	public int checkBlogStart(int travelNo) throws Exception;
	
	public void startBlog(int travelNo) throws Exception;
	
	public List<Integer> listPlaceNo(Search search) throws Exception;

	public List<Integer> listPlaceNoTemp(int travelNo) throws Exception;
	
	public void addTravel(Travel travel) throws Exception;
	public void addCity(City city) throws Exception;
	public void addPlace(Place place) throws Exception;
	
	public Travel getTravel(int travNo) throws Exception;
	public Travel getTravel(Travel travel) throws Exception;
	public List<City> getCity(int travNo) throws Exception;
	public Place getPlace(Place place) throws Exception;
	
	public Object getCityNo(String tNo ,String cId) throws Exception;
	
	
	public void addPlan(Travel travel, City city, Place place) throws Exception;
	
	public Map<String, Object> getListTravel(Search search) throws Exception;
	
	public List<City> blogCity(int travNo) throws Exception;
	public List<Place> blogPlace(int cityNo) throws Exception;
	
	public void updateThumbnail(Travel travel) throws Exception;
}
