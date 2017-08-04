package com.ustrip.service.plan;

import java.util.List;

import com.ustrip.common.Search;
import com.ustrip.service.domain.City;
import com.ustrip.service.domain.Place;
import com.ustrip.service.domain.Travel;

public interface PlanDAO {

	public List<Place> listPlace(int travelNo);
	
	public int checkBlogStart(int travelNo);
	
	public void startBlog(int travelNo);

	public List<Integer> listPlaceNo(Search search);

	public List<Integer> listPlaceNoTemp(int travelNo);
	
	public void addTravel(Travel travel) throws Exception;
	public void addCity(City city) throws Exception;
	public void addPlace(Place place) throws Exception;
		
	public Travel getTravel(Travel travel) throws Exception;
	public City getCity(City city) throws Exception;
	
	public Travel getTravel(int travNo) throws Exception;
	public List<City> getCity(int travNo) throws Exception;
	public Place getPlace(Place place) throws Exception;
	
	public Object getCityNo(String tNo ,String cId) throws Exception;
	
	public List<Travel> getListTravel(Search search) throws Exception;
	public int totalCount(Search search) throws Exception;
	
	public List<City> blogCity(int travNo) throws Exception;
	public List<Place> blogPlace(int cityNo) throws Exception;	
	
	public void updateThumbnail(Travel travel) throws Exception;
}
