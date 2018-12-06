package com.rx.savings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rx.savings.model.Pharmacy;

public interface RxSavingsRepository extends JpaRepository<Pharmacy, Long>{

}
