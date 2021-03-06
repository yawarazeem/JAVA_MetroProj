package com.station.service;

import java.sql.SQLException;
import java.util.Collection;

import com.metro.bean.Station;
import com.station.pesistence.StationDao;
import com.station.pesistence.StationDaoImpl;

public class StationServiceImpl implements StationService {
private StationDao stationDao = new StationDaoImpl();
	
	@Override
	public boolean addStation(String stationName) throws ClassNotFoundException, SQLException {
		return stationDao.addStation(stationName);
	
	}

	@Override
	public Collection<Station> getAllstations() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return stationDao.getAllRecords();
	}
}

