package com.yash.poc;

public class SeatCategory {

	private int id;
	private String name;
	private int numberOfSeatsInFirstRow;
	private int numberOfRowsForParticularCategory;

	public SeatCategory(int id, String name, int numberOfSeatsInFirstRow, int numberOfRowsForParticularCategory) {
		super();
		this.id = id;
		this.name = name;
		this.numberOfSeatsInFirstRow = numberOfSeatsInFirstRow;
		this.numberOfRowsForParticularCategory = numberOfRowsForParticularCategory;
	}

	public SeatCategory() {
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfSeatsInFirstRow() {
		return numberOfSeatsInFirstRow;
	}

	public int getNumberOfRowsForParticularCategory() {
		return numberOfRowsForParticularCategory;
	}
	
}
