package com.interview.jpmorgan.sensex.service;

public interface IStockTradeService {
	/** 
	 * Generates PERatio, Dividend, VolumeWeightedORice of each stock. And also calculates Index of SSSM
	 * 
	 */
	public void simulateTrading();
}
