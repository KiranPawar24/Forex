package com.synechron.forex.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "fx_transaction")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)
public class FxTransaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fxTransactionId;

	@Column(name = "holding_currency")
	private String holdingCurrency;

	@Column(name = "desired_currency")
	private String desiredCurrency;

	@Column(name = "transaction_value")
	private Long transactionValue;

	@JoinColumn(name = "account_id")
	@ManyToOne(targetEntity = Account.class)
	private Account account;

	@JoinColumn(name = "currency_pair_id")
	@ManyToOne(targetEntity = CurrencyPair.class)
	private CurrencyPair currencyPair;

	@Column(name = "created_at", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
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
