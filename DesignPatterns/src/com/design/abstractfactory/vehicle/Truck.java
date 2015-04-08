package com.design.abstractfactory.vehicle;

/**
 * This class represents a truck.
 * 
 * @author Jonathan Ware
 */
public class Truck implements Vehicle {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void manufacture() {
		System.out.println("Manufacturing a truck...");
	}
}
