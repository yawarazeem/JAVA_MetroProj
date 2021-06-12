package com.station.pesistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.metro.bean.Station;



public interface StationDao {
	boolean addStation(String stationName) throws ClassNotFoundException, SQLException;

	
	Collection<Station> getAllRecords() throws ClassNotFoundException, SQLException;
	
	
}
