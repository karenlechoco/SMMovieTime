package com.sm.database;

public class Schedule {

	String SpecificLocationCode;
	String CinemaCode;
	String MovieCode;
	String Date;
	String Time;
	Float TicketPrice;
	Integer Vacant_Seats;
	
	public String getSpecificLocationCode() {
		return SpecificLocationCode;
	}
	
	public void setSpecificLocationCode(String specificLocationCode) {
		SpecificLocationCode = specificLocationCode;
	}
	
	public String getCinemaCode() {
		return CinemaCode;
	}
	
	public void setCinemaCode(String cinemaCode) {
		CinemaCode = cinemaCode;
	}
	
	public String getMovieCode() {
		return MovieCode;
	}
	
	public void setMovieCode(String movieCode) {
		MovieCode = movieCode;
	}
	
	public String getDate() {
		return Date;
	}
	
	public void setDate(String date) {
		Date = date;
	}
	
	public String getTime() {
		return Time;
	}
	
	public void setTime(String time) {
		Time = time;
	}
	
	public Float getTicketPrice() {
		return TicketPrice;
	}
	
	public void setTicketPrice(Float ticketPrice) {
		TicketPrice = ticketPrice;
	}
	
	public Integer getVacant_Seats() {
		return Vacant_Seats;
	}
	
	public void setVacant_Seats(Integer vacant_Seats) {
		Vacant_Seats = vacant_Seats;
	}
	
}
