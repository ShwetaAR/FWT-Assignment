package com.yash.mbs.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class SeatCategory {

	private int id;
	private String name;
	private int numberOfRows;
	private int numberOfSeatInFirstRow;

	public SeatCategory(int id, String name, int numberOfRows, int numberOfSeatInFirstRow) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfRows = numberOfRows;
		this.numberOfSeatInFirstRow = numberOfSeatInFirstRow;
	}

	public SeatCategory() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public int getNumberOfSeatInFirstRow() {
		return numberOfSeatInFirstRow;
	}

}
