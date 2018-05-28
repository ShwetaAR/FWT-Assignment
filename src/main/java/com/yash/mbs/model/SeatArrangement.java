package com.yash.mbs.model;

public class SeatArrangement {

	private Screen screen;
	private Seat seat;
	private SeatCategory seatCategory;

	public SeatArrangement(Screen screen, Seat seat, SeatCategory seatCategory) {
		super();
		this.screen = screen;
		this.seat = seat;
		this.seatCategory = seatCategory;
	}

	public SeatArrangement() {
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public Seat getSeats() {
		return seat;
	}

	public void setSeats(Seat seat) {
		this.seat = seat;
	}

	public SeatCategory getSeatCategory() {
		return seatCategory;
	}

	public void setSeatCategory(SeatCategory seatCategory) {
		this.seatCategory = seatCategory;
	}

}
