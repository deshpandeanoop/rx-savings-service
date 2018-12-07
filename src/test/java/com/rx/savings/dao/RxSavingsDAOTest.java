package com.rx.savings.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rx.savings.idao.IRxSavingsDAO;
import com.rx.savings.response.PharmacyResponse;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RxSavingsDAOTest {
@Autowired
private IRxSavingsDAO rxSavingsDAO;
@Test
public void when_GetNearestPharmacy_Then_ReturnPharmacyDetailsWithDistance() {
	double latitude = 12.22;
	double longitude = -98.67;
	PharmacyResponse expectedNearestPharmacy = new PharmacyResponse("CVS PHARMACY", "5001 WEST 135 ST", 1856.23);
	PharmacyResponse returnedNearestPharmacy = rxSavingsDAO.getNearestPharmacy(latitude, longitude);
	assertEquals(expectedNearestPharmacy.getPharmacyName(), returnedNearestPharmacy.getPharmacyName());
	assertEquals(expectedNearestPharmacy.getAddress(), returnedNearestPharmacy.getAddress());
	assertEquals(expectedNearestPharmacy.getDistance(), returnedNearestPharmacy.getDistance(),0);
}
}
