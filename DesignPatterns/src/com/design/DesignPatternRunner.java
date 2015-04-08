package com.design;

import com.design.abstractfactory.AbstractFactoryRunner;
import com.design.factory.FactoryRunner;

/**
 * This class is the top application runner.
 * <p>
 * Each pattern is executed and output is displayed on the console.
 * 
 * @author Jonathan Ware
 */
public class DesignPatternRunner {
	
	/**
	 * Runner method.
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("---------------------");
		System.out.println("Using Factory Pattern");
		System.out.println("---------------------");
		FactoryRunner.main(args);
		System.out.println("");
		
		System.out.println("------------------------------");
		System.out.println("Using Abstract Factory Pattern");
		System.out.println("------------------------------");
		AbstractFactoryRunner.main(args);
		System.out.println("");
	}
}
