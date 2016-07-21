package com.interview.jpmorgan.sensex.util;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.interview.jpmorgan.sensex.model.Stock;
import com.interview.jpmorgan.sensex.model.enums.StockType;

public class StockValuatorTest {
	Stock testCommonStock = null;
	Stock testPreferredStock = null;
	@Before
	public void init(){
		testCommonStock = new Stock("Test",8.0, 0.0, 100,StockType.COMMON,100);
		testPreferredStock = new Stock("Test",8.0, 0.2, 100,StockType.PREFFERED,100);
	}
	
	@Test
	public void testCalculateDividend(){
		double expectedCommonDividend=8.0;
		double commonDividend=StockValuator.calculateDividend(testCommonStock);
		Assert.assertTrue(commonDividend==expectedCommonDividend);
		
		double expectedPreferredDividend=20.0;
		double preferredDividend=StockValuator.calculateDividend(testPreferredStock);
		Assert.assertTrue(preferredDividend==expectedPreferredDividend);
		
	}
	
	@Test
	public void testPE(){
		double expectedPE=12.5;
		double pe=StockValuator.calculatePERatio(testCommonStock);
		Assert.assertTrue(pe==expectedPE);		
	}
	
	@Test
	public void testCalcualteIndex(){
		double expectedIndex=100;
		Set<Stock> stocks = new HashSet<Stock>();
		stocks.add(testCommonStock);
		stocks.add(testPreferredStock);
		double index=StockValuator.calculateAllStockIndex(stocks);
		Assert.assertTrue(index==expectedIndex);		
	}
	
	
}

