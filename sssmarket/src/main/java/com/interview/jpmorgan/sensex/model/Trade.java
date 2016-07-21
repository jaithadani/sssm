package com.interview.jpmorgan.sensex.model;

import java.util.Date;

import com.interview.jpmorgan.sensex.model.enums.TradeType;

/**
 * Trade Model
 * @author jthadani
 *
 */
public class Trade {
	
	private double price;
	private int quantity;
	private TradeType tradeType;
	private Date timestamp;

	public Trade(double price, int quantity, TradeType tradeType,Date timestamp) {

		this.price = price;
		this.quantity = quantity;
		this.tradeType = tradeType;
		this.timestamp = timestamp;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
