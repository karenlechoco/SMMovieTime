package com.sm.database;

public class Schedule {

	String generalLocation;
	String specificLocation;
	String cinemaName;
	String movieCode;
	String date;
	String time;
	Float ticketPrice;
	Integer vacantSeats;
	
	public String getGeneralLocation() {
		return generalLocation;
	}
	
	public void setGeneralLocation(String generalLocation) {
		this.generalLocation = generalLocation;
	}
	
	public String getSpecificLocation() {
		return specificLocation;
	}
	
	public void setSpecificLocation(String specificLocation) {
		this.specificLocation = specificLocation;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public String getMovieCode() {
		return movieCode;
	}
	
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Float getTicketPrice() {
		return ticketPrice;
	}
	
	public void setTicketPrice(Float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	
	public Integer getVacantSeats() {
		return vacantSeats;
	}
	
	public void setVacantSeats(Integer vacantSeats) {
		this.vacantSeats = vacantSeats;
	}
	
}
