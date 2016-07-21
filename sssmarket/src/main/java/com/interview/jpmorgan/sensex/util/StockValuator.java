package com.interview.jpmorgan.sensex.util;

import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.joda.time.DateTime;

import com.interview.jpmorgan.sensex.model.Stock;
import com.interview.jpmorgan.sensex.model.Trade;

public class StockValuator{
	public static final int STOCK_WEIGHT_WINDOW=15;

	/**
	* Calculate the dividend based on the specified price
	* 
	* @param stock
	* @return The dividend
	* 
	*/
	public static double calculateDividend(final Stock stock) {
		double dividend=0;
		switch(stock.getStockType()) {
			case COMMON:
				dividend= stock.getLastDivident()*100/stock.getPrice();
				break;
			case PREFFERED:
				dividend= (stock.getFixedDivident()*stock.getParValue())*100/stock.getPrice();
				break;
		}
		dividend=  (double)Math.round(dividend*100)/100 ;
		return dividend;
	}
	
	/**
	* Calculate P/E Ratio
	* 
	* @param stock
	* @return The P/E Ratio
	*/
	public static double calculatePERatio(final Stock stock) {
		double peRatio= stock.getPrice()/stock.getLastDivident();
		peRatio=  (double)Math.round(peRatio*100)/100 ;
		return peRatio;
	}

	/**
	* Calculate the Volume Weighted Stock Price 
	* @param stock
	* @return The Volume Weighted Stock Price
	*/
	public static double calculateVolumeWeightedStockPrice(final Stock stock) {
		DateTime past= DateTime.now().minus(STOCK_WEIGHT_WINDOW);
		TreeMap<Long,Trade> trades = stock.getTrades();
		// Past 15 mins Trade
		SortedMap<Long, Trade> latestTrades = trades.tailMap(past.toDate().getTime());

		double totalPriceQty = 0.0;
		Integer totalQty = 0;
		for (Trade trade: latestTrades.values()) {
			totalPriceQty += trade.getPrice() * trade.getQuantity();
			totalQty += trade.getQuantity();
		}
		double volumeWeigthedPrice = (double)(totalPriceQty/totalQty);
		//Rounding off 2 decimal
		volumeWeigthedPrice= (double)Math.round(volumeWeigthedPrice*100)/100;
		return volumeWeigthedPrice;
	}
	
	/**
	 * Calculate All Stock Index using Geometric Mean
	 * @param stocks
	 * @return index of all Stocks
	 */
	public static double calculateAllStockIndex(final Set<Stock> stocks){
		double allStockPrices = 1;
		for(Stock stock:stocks){
			allStockPrices = (double)stock.getPrice()*allStockPrices;
		}
		
		double index =Math.pow(allStockPrices,(double)(1.0/stocks.size()));
		return (double)Math.round(index*100)/100;
	}
}
