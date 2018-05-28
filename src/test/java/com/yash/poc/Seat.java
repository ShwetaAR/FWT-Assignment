package com.yash.poc;

import java.util.List;

public class Seat {
	private int id;
	private List<Integer> seatNumber;
	private boolean isAvailable;
	private String rowName;
	public Seat() {
	}
	public Seat(int id, String rowName, List<Integer> seatNumber, boolean isAvailable) {
		super();
		this.id = id;
		this.rowName = rowName;
		this.seatNumber = seatNumber;
		this.isAvailable = isAvailable;
	}
	public int getId() {
		return id;
	}
	public String getRowNumber() {
		return rowName;
	}
	public List<Integer> getSeatNumber() {
		return seatNumber;
	}
	public boolean isAvailable() {
		return isAvailable;
	}

	
	
	
	

}
