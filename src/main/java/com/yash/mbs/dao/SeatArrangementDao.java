package com.yash.mbs.dao;

import java.util.List;

import com.yash.mbs.model.SeatArrangement;
import com.yash.mbs.model.SeatCategory;

public interface SeatArrangementDao {


	public int insertSeatArrangementCategoryWise(List<SeatArrangement> seatArrangementCategoryWise);

	public List<SeatArrangement> loadAllScreenArrangementCategoryWise();

}
