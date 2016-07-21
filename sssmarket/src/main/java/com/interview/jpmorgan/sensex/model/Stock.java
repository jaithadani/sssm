package com.interview.jpmorgan.sensex.model;

import java.util.TreeMap;

import com.interview.jpmorgan.sensex.model.enums.StockType;

/**
 * @author jthadani
 * Stock Model 
 */
public class Stock {
	
	public Stock(String symbol, double lastDivident, double fixedDivident, double parValue, StockType stockType,
			double price) {
		this.symbol = symbol;
		this.lastDivident = lastDivident;
		this.fixedDivident = fixedDivident;
		this.parValue = parValue;
		this.stockType = stockType;
		this.price = price;
	}
	
	private String symbol;
	private double lastDivident;
	private double fixedDivident;
	private double parValue;
	private StockType stockType;
	private double price;
	private TreeMap<Long,Trade> trades = new TreeMap<Long,Trade>();
	
	public StockType getStockType() {
		return stockType;
	}
	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getLastDivident() {
		return lastDivident;
	}
	public void setLastDivident(double lastDivident) {
		this.lastDivident = lastDivident;
	}
	public double getFixedDivident() {
		return fixedDivident;
	}
	public void setFixedDivident(double fixedDivident) {
		this.fixedDivident = fixedDivident;
	}
	public double getParValue() {
		return parValue;
	}
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [symbol=");
		builder.append(symbol);
		builder.append(", lastDivident=");
		builder.append(lastDivident);
		builder.append(", fixedDivident=");
		builder.append(fixedDivident);
		builder.append(", parValue=");
		builder.append(parValue);
		builder.append(", stockType=");
		builder.append(stockType);
		builder.append("]");
		return builder.toString();
	}
	public TreeMap<Long, Trade> getTrades() {
		return trades;
	}
	public void setTrades(TreeMap<Long, Trade> trades) {
		this.trades = trades;
	}
	
	
}
