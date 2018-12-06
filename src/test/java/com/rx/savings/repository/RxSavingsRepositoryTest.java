package com.rx.savings.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rx.savings.model.Pharmacy;
@RunWith(SpringRunner.class)
@DataJpaTest
public class RxSavingsRepositoryTest {
	@Autowired
	private RxSavingsRepository rxSavingsRepository;
	@Test
	public void whenFindAll_ThenReturnPharmacies() {
		List<Pharmacy> pharmacies = rxSavingsRepository.findAll();
		assertEquals(30, pharmacies.size());
	}

}
