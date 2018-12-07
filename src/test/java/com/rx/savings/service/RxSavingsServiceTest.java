package com.rx.savings.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rx.savings.idao.IRxSavingsDAO;
import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.response.PharmacyResponse;
@RunWith(SpringRunner.class)
@SpringBootTest
public class RxSavingsServiceTest {
	@Autowired
	private IRxSavingsService rxSavingsService;
	@MockBean
	private IRxSavingsDAO rxSavingsDAO;
	private final double  latitude = 12.22;
	private final double  longitude = -98.67;
	
	@Before
	public void setUp() {
		Mockito.when(rxSavingsDAO.getNearestPharmacy(latitude, longitude))
							.thenReturn(new PharmacyResponse("Rx", "Rx Address", 123.12));
	}
	@Test
	public void when_GetNearestPharmacy_Then_ReturnPhamarcyDetailsWithDistance() {
		PharmacyResponse nearestPharmacy = rxSavingsService.getNearestPharmacy(latitude, longitude);
		assertEquals("Rx", nearestPharmacy.getPharmacyName());
		assertEquals("Rx Address", nearestPharmacy.getAddress());
		assertEquals(123.12, nearestPharmacy.getDistance(), 0);
	}
}
