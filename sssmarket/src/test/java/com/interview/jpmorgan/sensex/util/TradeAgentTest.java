package com.interview.jpmorgan.sensex.util;

import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.interview.jpmorgan.sensex.model.Stock;
import com.interview.jpmorgan.sensex.model.Trade;
import com.interview.jpmorgan.sensex.model.enums.StockType;

public class TradeAgentTest {
	Stock testStock = null;
	@Before
	public void init(){
		testStock = new Stock("Test",8.0, 0.0, 100,StockType.COMMON,100);
	}
	
	@Test
	public void testBuy(){
		Date date = new Date();
		double testPrice=99.0;
		TradeAgent.buy(testStock, 10, testPrice, date);
		Assert.assertTrue(testStock.getPrice()==testPrice);
		
		Map<Long,Trade> trades=testStock.getTrades();
		Assert.assertFalse(trades.isEmpty());
		
		Trade trade = trades.get(date.getTime());
		Assert.assertNotNull(trade);
	}
	
	@Test
	public void testSell(){
		Date date = new Date();
		double testPrice=99.0;
		TradeAgent.sell(testStock, 10, testPrice, date);
		Assert.assertTrue(testStock.getPrice()==testPrice);
		
		Map<Long,Trade> trades=testStock.getTrades();
		Assert.assertFalse(trades.isEmpty());
		
		Trade trade = trades.get(date.getTime());
		Assert.assertNotNull(trade);
	}
}

