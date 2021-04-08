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

import com.synechron.forex.dao.FxTransactionRepository;
import com.synechron.forex.models.FxTransaction;

@RestController
@RequestMapping(value = "/api")
public class FxTransactionController {

	@Autowired
	private FxTransactionRepository fxTransactionRepo;

	@PostMapping("/save-fx-transaction")
	public ResponseEntity<FxTransaction> saveFxTransaction(@Valid @RequestBody FxTransaction fxTransaction) {
		try {
			FxTransaction fxTrans = fxTransactionRepo.save(fxTransaction);
			return new ResponseEntity<FxTransaction>(fxTrans, null, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<FxTransaction>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-fx-transaction/{id}")
	public ResponseEntity<FxTransaction> updateFxTransaction(@PathVariable(value = "id") Long fxTransactionId,
			@Valid @RequestBody FxTransaction fxTransaction) {
		try {
			FxTransaction fxTrans = fxTransactionRepo.findOne(fxTransactionId);
			fxTrans.setHoldingCurrency(fxTransaction.getHoldingCurrency());
			fxTrans.setDesiredCurrency(fxTransaction.getDesiredCurrency());
			fxTrans.setTransactionValue(fxTransaction.getTransactionValue());
			fxTrans.setAccount(fxTransaction.getAccount());
			fxTrans.setCurrencyPair(fxTransaction.getCurrencyPair());
			FxTransaction result = fxTransactionRepo.save(fxTrans);
			return new ResponseEntity<FxTransaction>(result, null, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<FxTransaction>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-fx-transaction/{id}")
	public ResponseEntity<FxTransaction> deleteFxTransaction(@PathVariable(value = "id") Long fxTransactionId) {
		try {
			FxTransaction fxTransaction = fxTransactionRepo.findOne(fxTransactionId);
			fxTransactionRepo.delete(fxTransaction);
			return new ResponseEntity<FxTransaction>(null, null, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<FxTransaction>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search-fx-transaction")
	public ResponseEntity<List<FxTransaction>> searchFxTransaction(@RequestParam Long currencyPairId,
			@RequestParam Long transactionValue) {
		try {
			List<FxTransaction> list = fxTransactionRepo.searchFxTransaction(currencyPairId, transactionValue);
			return new ResponseEntity<List<FxTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/get-all-fx-transaction")
	public ResponseEntity<List<FxTransaction>> getAllFxTransaction(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			Pageable paging = new PageRequest(pageNo, pageSize, new Sort("createdAt"));
			Page<FxTransaction> pagedResult = fxTransactionRepo.findAll(paging);
			List<FxTransaction> list = pagedResult.getContent();
			return new ResponseEntity<List<FxTransaction>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
