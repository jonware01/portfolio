package com.design.abstractfactory.color;

/**
 * Represents the color red
 * 
 * @author Jonathan Ware
 */
public class Red implements Color {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint() {
		System.out.println("Painting it red...");
	}
}
