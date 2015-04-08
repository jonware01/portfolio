package com.design.abstractfactory.vehicle;

/**
 * This class represents a van.
 * 
 * @author Jonathan Ware
 */
public class Van implements Vehicle {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void manufacture() {
		System.out.println("Manufacturing a van...");
	}
}
