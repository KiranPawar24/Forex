package com.synechron.forex.dto;

import java.io.Serializable;
import java.util.Date;

public class CurrencyPairDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long currencyPairId;

	private String baseCurrency;

	private String quoteCurrency;

	private Float rate;

	private Date createdAt;

	private Date updatedAt;

	public Long getCurrencyPairId() {
		return currencyPairId;
	}

	public void setCurrencyPairId(Long currencyPairId) {
		this.currencyPairId = currencyPairId;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public String getQuoteCurrency() {
		return quoteCurrency;
	}

	public void setQuoteCurrency(String quoteCurrency) {
		this.quoteCurrency = quoteCurrency;
	}

	public Float getRate() {
		return rate;
	}

	public void setRate(Float rate) {
		this.rate = rate;
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
