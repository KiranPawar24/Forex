package com.synechron.forex.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synechron.forex.dao.CurrencyPairRepository;
import com.synechron.forex.models.CurrencyPair;
import com.synechron.forex.service.CurrencyPairService;

@RestController
@RequestMapping(value = "/api")
public class CurrencyPairController {

	@Autowired
	private CurrencyPairRepository currencyPairRepo;

	@Autowired
	private CurrencyPairService currencyPairService;

	@PostMapping("/add-currency-pair")
	public ResponseEntity<CurrencyPair> createCurrencyPair(@Valid @RequestBody CurrencyPair currencyPair) {
		try {
			CurrencyPair cp = currencyPairService.addCurrencyPair(currencyPair);
			if (cp != null) {
				return new ResponseEntity<CurrencyPair>(cp, null, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<CurrencyPair>(null, null, HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CurrencyPair>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-currency-pair/{id}")
	public ResponseEntity<CurrencyPair> updateCurrencyPair(@PathVariable(value = "id") Long currencyPairId,
			@Valid @RequestBody CurrencyPair currencyPair) {
		try {
			CurrencyPair cp = currencyPairRepo.findOne(currencyPairId);
			cp.setBaseCurrency(currencyPair.getBaseCurrency());
			cp.setQuoteCurrency(currencyPair.getQuoteCurrency());
			cp.setRate(currencyPair.getRate());
			CurrencyPair result = currencyPairRepo.save(cp);
			return new ResponseEntity<CurrencyPair>(result, null, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CurrencyPair>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-currency-pair/{id}")
	public ResponseEntity<CurrencyPair> deleteCurrencyPair(@PathVariable(value = "id") Long CurrencyPairId) {
		try {
			CurrencyPair cp = currencyPairRepo.findOne(CurrencyPairId);
			currencyPairRepo.delete(cp);
			return new ResponseEntity<CurrencyPair>(null, null, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CurrencyPair>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search-currency-pair")
	public ResponseEntity<List<CurrencyPair>> searchCurrency(@RequestParam String currency) {
		try {
			List<CurrencyPair> list = currencyPairRepo.searchCurrencyPair(currency);
			return new ResponseEntity<List<CurrencyPair>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get-all-currency-pairs")
	public ResponseEntity<List<CurrencyPair>> getAllCurrencyPairs(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			Pageable paging = new PageRequest(pageNo, pageSize, new Sort("rate"));
			Page<CurrencyPair> pagedResult = currencyPairRepo.findAll(paging);
			List<CurrencyPair> list = pagedResult.getContent();
			return new ResponseEntity<List<CurrencyPair>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
