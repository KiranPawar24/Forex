package com.synechron.forex.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.synechron.forex.models.CurrencyPair;

@Repository
public interface CurrencyPairRepository extends PagingAndSortingRepository<CurrencyPair, Long> {

	@Query(value = "SELECT * FROM currency_pair cp WHERE cp.base_currency = ?1 and cp.quote_currency = ?2", nativeQuery = true)
	CurrencyPair checkCurrencyPair(String baseCurrency, String quoteCurrency);

	@Query(value = "SELECT * FROM currency_pair cp WHERE cp.base_currency = ?1 or cp.quote_currency = ?1 ORDER BY rate DESC", nativeQuery = true)
	List<CurrencyPair> searchCurrencyPair(String currency);
}
