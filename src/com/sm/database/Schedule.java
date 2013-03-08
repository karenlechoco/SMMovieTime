package com.sm.database;

public class Schedule {

	String generalLocation;
	String specificLocation;
	String cinemaName;
	String cinemaType;
	String movieName;
	String date;
	String time;
	Double ticketPrice;
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
		
	public String getCinemaType() {
		return cinemaType;
	}

	public void setCinemaType(String cinemaType) {
		this.cinemaType = cinemaType;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
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
	
	public Double getTicketPrice() {
		return ticketPrice;
	}
	
	public void setTicketPrice(double d) {
		this.ticketPrice = d;
	}
	
	public Integer getVacantSeats() {
		return vacantSeats;
	}
	
	public void setVacantSeats(Integer vacantSeats) {
		this.vacantSeats = vacantSeats;
	}
	
}
