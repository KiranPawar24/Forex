package com.synechron.forex.service;

import java.util.List;

import com.synechron.forex.models.CurrencyPair;

public interface CurrencyPairService {
	
	public CurrencyPair addCurrencyPair(CurrencyPair currencypair);

	public List<CurrencyPair> getCurrencyPair();

}
