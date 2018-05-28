package com.yash.mbs.model;

import com.yash.mbs.enumeration.SeatAvailability;

public class Seat {
	private int id;
	private int[][] seatNumber;
	private String seatAvailability;

	public Seat(int id, int[][] seatNumber, String seatAvailability) {
		super();
		this.id = id;
		this.seatNumber = seatNumber;
		this.seatAvailability = seatAvailability;
	}

	public Seat() {
	}

	public int getId() {
		return id;
	}

	public int[][] getSeatNumber() {
		return seatNumber;
	}

	public String getSeatAvailability() {
		return seatAvailability;
	}

}
