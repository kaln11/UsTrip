package com.ustrip.service.domain;

import java.util.Date;

public class Place {
	

	private int placeNo;
	private int cityNo; //수정해야함
	private int travelNo; //수정해야함
	private String placeId;
	private String place;
	private double placeX;
	private double placeY;
	private int prePlaceNo;
	private int nextPlaceNo;
	private String memo;
	private Date visitDate;
	
	public Place(){
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	public int getPlaceNo() {
		return placeNo;
	}

	public void setPlaceNo(int placeNo) {
		this.placeNo = placeNo;
	}

	public int getCityNo() {
		return cityNo;
	}

	public void setCityNo(int cityNo) {
		this.cityNo = cityNo;
	}

	public int getTravelNo() {
		return travelNo;
	}

	public void setTravelNo(int travelNo) {
		this.travelNo = travelNo;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public double getPlaceX() {
		return placeX;
	}

	public void setPlaceX(double placeX) {
		this.placeX = placeX;
	}

	public double getPlaceY() {
		return placeY;
	}

	public void setPlaceY(double placeY) {
		this.placeY = placeY;
	}

	public int getPrePlaceNo() {
		return prePlaceNo;
	}

	public void setPrePlaceNo(int prePlaceNo) {
		this.prePlaceNo = prePlaceNo;
	}

	public int getNextPlaceNo() {
		return nextPlaceNo;
	}

	public void setNextPlaceNo(int nextPlaceNo) {
		this.nextPlaceNo = nextPlaceNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	@Override
	public String toString() {
		return "Place [placeNo=" + placeNo + ", cityNo=" + cityNo + ", travelNo=" + travelNo + ", placeId=" + placeId
				+ ", place=" + place + ", placeX=" + placeX + ", placeY=" + placeY + ", prePlaceNo=" + prePlaceNo
				+ ", nextPlaceNo=" + nextPlaceNo + ", memo=" + memo + ", visitDate=" + visitDate + "]";
	}
	

}
