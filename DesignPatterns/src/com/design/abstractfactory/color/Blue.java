package com.design.abstractfactory.color;

/**
 * Represents the color blue
 * 
 * @author Jonathan Ware
 */
public class Blue implements Color {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint() {
		System.out.println("Painting it blue...");
	}
}
