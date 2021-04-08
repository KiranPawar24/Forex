package com.synechron.forex.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.forex.dao.CurrencyPairRepository;
import com.synechron.forex.models.CurrencyPair;
import com.synechron.forex.service.CurrencyPairService;

@Service("currencyPairService")
public class CurrencyPairServiceImpl implements CurrencyPairService {

	@Autowired
	private CurrencyPairRepository currencyPairRepository;

	@Override
	public CurrencyPair addCurrencyPair(CurrencyPair currencypair) {
		try {
			CurrencyPair cp = currencyPairRepository.checkCurrencyPair(currencypair.getBaseCurrency(),
					currencypair.getQuoteCurrency());
			CurrencyPair rcp = currencyPairRepository.checkCurrencyPair(currencypair.getQuoteCurrency(),
					currencypair.getBaseCurrency());
			if (cp == null && rcp == null) {
				return currencyPairRepository.save(currencypair);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<CurrencyPair> getCurrencyPair() {
		// Iterable<CurrencyPair> currencyPairList = currencyPairRepository.findAll();
		// List<CurrencyPair> result =
		// StreamSupport.stream(currencyPairList.spliterator(), false)
		// .collect(Collectors.toList());
		// List<CurrencyPairDto> resultList = new ArrayList<>();
		// for (CurrencyPair currencyPair : result) {
		// resultList.add(new DozerBeanMapper().map(currencyPair,
		// CurrencyPairDto.class));
		// }
		// return resultList;
		try {
			Iterable<CurrencyPair> currencyPairList = currencyPairRepository.findAll();
			List<CurrencyPair> result = StreamSupport.stream(currencyPairList.spliterator(), false)
					.collect(Collectors.toList());
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

}
