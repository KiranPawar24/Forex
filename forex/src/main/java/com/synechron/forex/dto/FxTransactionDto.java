package com.synechron.forex.dto;

import java.io.Serializable;
import java.util.Date;

import com.synechron.forex.models.Account;
import com.synechron.forex.models.CurrencyPair;

public class FxTransactionDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long fxTransactionId;

	private String holdingCurrency;

	private String desiredCurrency;

	private Long transactionValue;

	private Account account;

	private CurrencyPair currencyPair;

	private Date createdAt;

	private Date updatedAt;

	public Long getFxTransactionId() {
		return fxTransactionId;
	}

	public void setFxTransactionId(Long fxTransactionId) {
		this.fxTransactionId = fxTransactionId;
	}

	public String getHoldingCurrency() {
		return holdingCurrency;
	}

	public void setHoldingCurrency(String holdingCurrency) {
		this.holdingCurrency = holdingCurrency;
	}

	public String getDesiredCurrency() {
		return desiredCurrency;
	}

	public void setDesiredCurrency(String desiredCurrency) {
		this.desiredCurrency = desiredCurrency;
	}

	public Long getTransactionValue() {
		return transactionValue;
	}

	public void setTransactionValue(Long transactionValue) {
		this.transactionValue = transactionValue;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public CurrencyPair getCurrencyPair() {
		return currencyPair;
	}

	public void setCurrencyPair(CurrencyPair currencyPair) {
		this.currencyPair = currencyPair;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
