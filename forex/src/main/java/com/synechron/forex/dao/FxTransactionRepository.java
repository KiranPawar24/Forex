package com.synechron.forex.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.synechron.forex.models.FxTransaction;

@Repository
public interface FxTransactionRepository extends PagingAndSortingRepository<FxTransaction, Long> {

	@Query(value = "SELECT * FROM fx_transaction fx WHERE fx.currency_pair_id =?1 AND fx.transaction_value=?2 OREDER BY currency_pair_id, created_at", nativeQuery = true)
	public List<FxTransaction> searchFxTransaction(Long currencyPairId, Long transactionValue);

}
