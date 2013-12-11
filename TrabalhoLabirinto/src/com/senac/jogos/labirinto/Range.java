package com.senac.jogos.labirinto;

import java.util.Date;
import java.util.Random;

public class Range {
	
	private int min;
	private int max;
	private static Random randomGenerator = null;
	
	public Range(int min, int max)
	{
		this.min = min;
		this.max = max;
		if(randomGenerator == null)
			randomGenerator = new Random();
		this.randomGenerator.setSeed((new Date()).getTime());
	}
	
	public int getMin()
	{
		return min;
	}
	
	public int getMax()
	{
		return max;
	}
	
	public int getRandom()
	{
		int range = max - min;
		return randomGenerator.nextInt(range + 1) + min;
	}
	
	public static int getPercentual()
	{
		return (new Range(0,100)).getRandom();
	}
}
