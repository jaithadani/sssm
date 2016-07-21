package com.interview.jpmorgan.sensex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.interview.jpmorgan.sensex.service.IStockTradeService;
import com.interview.jpmorgan.sensex.service.impl.StockTradeService;

public class SuperSimpleSensex {
	static Logger LOGGER= LoggerFactory.getLogger(SuperSimpleSensex.class);
	/**
	 * Main Application Run.
	 * Note:- Could have used Spring but wanted to make it least dependent on any third party framework 
	 * and use core Java as much possible	
	 * @param args
	 */
	public static void main(String args[]){
		LOGGER.info("Super Simple Stock Market starts here");
		IStockTradeService stockTradeService = StockTradeService.getInstance();
		LOGGER.debug("Invoking Stock Trading Service");
		stockTradeService.simulateTrading();
	}

}
