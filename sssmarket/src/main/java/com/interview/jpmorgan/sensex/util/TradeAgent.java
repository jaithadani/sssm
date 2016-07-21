package com.interview.jpmorgan.sensex.util;

import java.util.Date;

import com.interview.jpmorgan.sensex.model.Stock;
import com.interview.jpmorgan.sensex.model.Trade;
import com.interview.jpmorgan.sensex.model.enums.TradeType;


public class TradeAgent{
	
	/**
	 * Buy a Stock at offered price
	 * @param stock
	 * @param quantity
	 * @param offerPrice
	 */
	public static void buy(Stock stock,final Integer quantity,final Double offerPrice, final Date localTime) {
		Trade trade = new Trade(offerPrice,quantity,TradeType.BUY,localTime);
		stock.getTrades().put(localTime.getTime(), trade);
		stock.setPrice(offerPrice);
	}

	/**
	 * Sell a stock at offered price
	 * @param stock
	 * @param quantity
	 * @param offerPrice
	 */
	public static void sell(Stock stock,final Integer quantity, final Double offerPrice,final Date localTime) {
		Trade trade = new Trade(offerPrice,quantity,TradeType.SELL,localTime);
		stock.getTrades().put(localTime.getTime(), trade);
		stock.setPrice(offerPrice);
	}
}
