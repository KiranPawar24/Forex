package com.synechron.forex.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.synechron.forex.models.Account;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

	@Query(value = "SELECT * FROM account a WHERE a.account_no = ?1", nativeQuery = true)
	Account checkAccount(Long accountNo);
}
