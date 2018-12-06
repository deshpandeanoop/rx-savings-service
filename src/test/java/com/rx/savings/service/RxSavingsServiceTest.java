package com.rx.savings.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.model.Pharmacy;
import com.rx.savings.repository.RxSavingsRepository;
import com.rx.savings.response.PharmacyResponse;
@RunWith(SpringRunner.class)
public class RxSavingsServiceTest {
	@Autowired
	private IRxSavingsService rxService;
	@MockBean
	private RxSavingsRepository rxSavingsRepository;
	@TestConfiguration
	public static class RxSavingsTestConfiguration {
		@Bean
		public IRxSavingsService rxSavingsService() {
			return new RxSavingsService();
		}
	}
	@Before
	public void setUp() {
		List<Pharmacy> pharmacies = new ArrayList<>();
		Pharmacy rx = new Pharmacy();
		rx.setAddress("Address test1");
		rx.setName("Rx Test1");
		rx.setLatitude(39.02419300);
		rx.setLongitude(-94.25503000);
		pharmacies.add(rx);
	
		rx = new Pharmacy();
		rx.setAddress("Address test2");
		rx.setName("Rx Test2");
		rx.setLatitude(39.00142300);
		rx.setLongitude(-95.68695000);
		pharmacies.add(rx);
		Mockito.when(rxSavingsRepository.findAll()).thenReturn(pharmacies);
	}

	@Test
	public void whenValidUserLocatIsSent_PharmacyDetailsWithDistanceIsReturned() {
		double latitude = 12.22;
		double longitude = -23.567;
		PharmacyResponse rxResponse = rxService.getNearestPharmacy(latitude, longitude);
		assertEquals("Rx Test1", rxResponse.getPharmacyName());
		assertEquals("Address test1", rxResponse.getAddress());
	}

}
