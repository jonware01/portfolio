package com.design.abstractfactory.color;

/**
 * Represents the color green
 * 
 * @author Jonathan Ware
 */
public class Green implements Color {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void paint() {
		System.out.println("Painting it green...");
	}
}
