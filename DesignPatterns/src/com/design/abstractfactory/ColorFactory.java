package com.design.abstractfactory;

import com.design.abstractfactory.color.Blue;
import com.design.abstractfactory.color.Color;
import com.design.abstractfactory.color.Green;
import com.design.abstractfactory.color.Red;
import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is the factory used to retrieve a color.
 * 
 * @author Jonathan Ware
 */
public class ColorFactory extends AbstractFactory {
	
	/**
	 * {@inheritDoc}
	 */
	public Vehicle getVehicle(String vehicleType)
	{
		return null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Color getColor(String colorType)
	{		
		if("red".equalsIgnoreCase(colorType))
		{
			return new Red();
		}
		
		if("green".equalsIgnoreCase(colorType))
		{
			return new Green();
		}

		if("blue".equalsIgnoreCase(colorType))
		{
			return new Blue();
		}
		
		throw new IllegalArgumentException("No such color");
	}
}
