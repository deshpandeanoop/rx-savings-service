package com.rx.savings.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rx.savings.iservice.IRxSavingsService;
import com.rx.savings.response.PharmacyResponse;
@RunWith(SpringRunner.class)
@WebMvcTest({RxSavingsController.class,RxSavingsErrorController.class})
public class RxSavingsControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IRxSavingsService rxSavingsService;
	private final double latitude = 12.22;
	private final double longitude = 97.45;
	@Before
	public void setUp() {
		Mockito.when(rxSavingsService.getNearestPharmacy(latitude, longitude))
		.thenReturn(new PharmacyResponse("Rx", "Rx Address", 123.43));
	}
	@Test
	public void when_getNearestRx_WithValidParams_ReturnOkResponse() throws Exception {
		mockMvc.perform(get("/pharmacy?latitude=12.22&longitude=97.45")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	@Test
	public void when_getNearestRx_WithOutOfRangeParams_ReturnBadResponse() throws Exception {
		mockMvc.perform(get("/pharmacy?latitude=-92.22&longitude=97.45")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
