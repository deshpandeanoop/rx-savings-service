package com.rx.savings.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rx.savings.idao.IRxSavingsDAO;
import com.rx.savings.response.PharmacyResponse;
@Repository
public class RxSavingsRepository implements IRxSavingsDAO{
	private final Logger LOGGER = LoggerFactory.getLogger(RxSavingsRepository.class);
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final String GET_NEAREST_LOC_QRY = "SELECT name, address, "+
		     " ROUND(69.00016404* DEGREES(ACOS(COS(RADIANS(:user_lat)) "+
		           "      * COS(RADIANS(latitude)) "+
		                " * COS(RADIANS(:user_long) - RADIANS(longitude)) "+
		                " + SIN(RADIANS(:user_lat)) "+
		                " * SIN(RADIANS(latitude)))),2) AS distance "+
		 " FROM pharmacy "+
		 " ORDER BY distance LIMIT 1";
	public RxSavingsRepository(NamedParameterJdbcTemplate namedParamterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParamterJdbcTemplate;
	}
	@Override
	public PharmacyResponse getNearestPharmacy(double latitude, double longitude) {
		LOGGER.debug("Fetching nearest pharmacy for lattitude : "+latitude+ " longitude : "+longitude);
		return namedParameterJdbcTemplate.queryForObject(GET_NEAREST_LOC_QRY, getParameterMap(latitude, longitude), new PharmacyMapper());
	}

	private class PharmacyMapper implements RowMapper<PharmacyResponse>{
		@Override
		public PharmacyResponse mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			PharmacyResponse rxResponse = new PharmacyResponse(resultSet.getString("name"),
					resultSet.getString("address"),resultSet.getDouble("distance"));
			return rxResponse;
		}
	}
	
	private Map<String,Double> getParameterMap(double latitude,double longitude){
		Map<String, Double> parameterMap = new HashMap<>();
		parameterMap.put("user_lat", latitude);
		parameterMap.put("user_long", longitude);
		return parameterMap;
	}
}
