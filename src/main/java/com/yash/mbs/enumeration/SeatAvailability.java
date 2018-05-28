package com.yash.mbs.enumeration;

public enum SeatAvailability {
	 AVAILBLE("A"), NOT_AVAILABLE("NA");

    private String seatValue;

    SeatAvailability(String seatValue) {
        this.seatValue = seatValue;
    }

    public String getValue() {
        return seatValue;
    }
	
	
	

}
