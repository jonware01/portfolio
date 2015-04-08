package com.design.abstractfactory.vehicle;

/**
 * This class represents a car.
 * 
 * @author Jonathan Ware
 */
public class Car implements Vehicle {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void manufacture() {
		System.out.println("Manufacturing a car...");
	}
}
