package com.interview.jpmorgan.sensex.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.jpmorgan.sensex.model.Stock;
import com.interview.jpmorgan.sensex.model.enums.StockType;
import com.interview.jpmorgan.sensex.service.IStockTradeService;
import com.interview.jpmorgan.sensex.util.StockValuator;
import com.interview.jpmorgan.sensex.util.TradeAgent;

public class StockTradeService implements IStockTradeService{
	private Set<Stock> stocks = new HashSet<Stock>();
	static Logger LOGGER = LoggerFactory.getLogger(StockTradeService.class);
	private static StockTradeService stockTradeService = null;
	private StockTradeService(){	
		loadStocks();
	}
	
	/** 
	 * Create Single Instance of TRadeService
	 * @return StockTradeService
	 */
	public static StockTradeService getInstance(){
		if(stockTradeService==null){
			synchronized(StockTradeService.class)
			{
				if(stockTradeService==null){
					LOGGER.debug("Creating instance of StockTradeService");
					stockTradeService= new StockTradeService();
				}
			}
		}
		return stockTradeService;
	}
	
	/** 
	 * Generates PERatio, Divident, VolumeWeightedORice of each stock. And also calculates Index of SSSM
	 * 
	 */
	@Override
	public void simulateTrading(){
		LOGGER.info("Trading Starts..");
		for(Stock stock:stocks){
			double peRatio=StockValuator.calculatePERatio(stock);
			double dividend = StockValuator.calculateDividend(stock);
			stock.setLastDivident(dividend);
			tradeStock(stock);
			double volumeWeightedStock = StockValuator.calculateVolumeWeightedStockPrice(stock);
			LOGGER.info("Stock {} --> P/E ={}, Divident={}, VolumeWeighted={}",stock.getSymbol(),
					peRatio,dividend,volumeWeightedStock);
		}
		double allStockIndex = StockValuator.calculateAllStockIndex(stocks);
		LOGGER.info("ALL STOCK INDEX FOR SSSM {}",allStockIndex);
	}
	
	
	/**
	 * Loads all stocks as per exercise and generate a random price for each stock 
	 */
	private void loadStocks(){
		LOGGER.info("Loading Stocks...... TEA,POP,ALE,GIN,JOE");
		stocks.add(new Stock("TEA",0.0, 0.0, 100,StockType.COMMON,getRandomValue(100)));
		stocks.add(new Stock("POP",8.0, 0.0, 100,StockType.COMMON,getRandomValue(100)));
		stocks.add(new Stock("ALE",23.0, 0.0, 60,StockType.COMMON,getRandomValue(60)));
		stocks.add(new Stock("GIN",8.0, 0.2, 100,StockType.PREFFERED,getRandomValue(100)));
		stocks.add(new Stock("JOE",13.0, 0.0, 250,StockType.COMMON,getRandomValue(250)));
		LOGGER.info("Stocks Loaded");
	}
	
	/**
	 * Generates Random Price which should be currentPrice (+/-)5. 
	 * @param parValue
	 * @return
	 */
	private double getRandomValue(double currentPrice) {
		Random r = new Random();
		int randomPrice= (int)Math.round(currentPrice);
		//Generated random Price should always be greater than 0;
		do{
			randomPrice = randomPrice + (r.nextInt(11)-5);
		} while (randomPrice<0);
		return (double)randomPrice;
	}
	/**
	 * Trade Stocks for 15 times over window of 15 mins.
	 * @param stock
	 */
	private void tradeStock(Stock stock){
		LOGGER.debug("Trading Stock {}"+stock);
		int i=0;
		DateTime now = DateTime.now();
		while(i<StockValuator.STOCK_WEIGHT_WINDOW){
			double offerPrice=getRandomValue(stock.getPrice());
			int quantity = (int)getRandomValue(20);
			Date timeinPast=now.minusMinutes(i).toDate();
			LOGGER.info("{}) Stock {} Being traded at OfferPrice {} and Share Quanity {} at {}",i+1,stock.getSymbol(),offerPrice,quantity,timeinPast);
			TradeAgent.buy(stock, quantity, offerPrice,timeinPast);
			TradeAgent.sell(stock, quantity, offerPrice, timeinPast);
			i++;
		}
	}
	
	
}
