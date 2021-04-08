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

import com.synechron.forex.dao.AccountRepository;
import com.synechron.forex.models.Account;
import com.synechron.forex.service.AccountService;

@RestController
@RequestMapping(value = "/api")
public class AccountController {

	@Autowired
	AccountRepository accountRepo;

	@Autowired
	AccountService accountService;

	@PostMapping("/add-account")
	public ResponseEntity<Account> createAccount(@Valid @RequestBody Account account) {
		try {
			Account ac = accountService.addAccount(account);
			if (ac != null) {
				return new ResponseEntity<>(ac, null, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(null, null, HttpStatus.ALREADY_REPORTED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update-account/{id}")
	public ResponseEntity<Account> updateAccount(@PathVariable(value = "id") Long accountId,
			@Valid @RequestBody Account account) {
		try {
			Account ac = accountRepo.findOne(accountId);
			ac.setAccountNo(account.getAccountNo());
			ac.setCustomerName(account.getCustomerName());
			ac.setBankName(account.getBankName());
			Account result = accountRepo.save(ac);
			return new ResponseEntity<Account>(result, null, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Account>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete-account/{id}")
	public ResponseEntity<Account> deleteAccount(@PathVariable(value = "id") Long accountId) {
		try {
			Account ac = accountRepo.findOne(accountId);
			accountRepo.delete(ac);
			return new ResponseEntity<Account>(null, null, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Account>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-all-account")
	public ResponseEntity<List<Account>> getAllAccount(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		try {
			Pageable paging = new PageRequest(pageNo, pageSize, new Sort("createdAt"));
			Page<Account> pagedResult = accountRepo.findAll(paging);
			List<Account> list = pagedResult.getContent();
			return new ResponseEntity<List<Account>>(list, new HttpHeaders(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
