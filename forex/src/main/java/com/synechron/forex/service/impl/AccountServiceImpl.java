package com.synechron.forex.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.forex.dao.AccountRepository;
import com.synechron.forex.models.Account;
import com.synechron.forex.service.AccountService;

@Service("accountRepo")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account addAccount(Account account) {
		try {
			Account ac = accountRepository.checkAccount(account.getAccountNo());
			if (ac != null) {
				return null;
			} else {
				return accountRepository.save(account);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
