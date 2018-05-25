package com.yash.mbs.enumeration;

public enum SeatCategory {
	PREMIUM(120), SILVER(70), GOLD(200);

	private int price;

	SeatCategory(int price) {
		this.price = price;
	}

	public int price() {
		return price;
	}

}
