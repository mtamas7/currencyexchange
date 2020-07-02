package com.wup.homework.currencyexchange.database.repository;

import com.wup.homework.currencyexchange.database.model.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
}
