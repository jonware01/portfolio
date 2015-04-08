package com.design.abstractfactory;

import com.design.abstractfactory.color.Color;
import com.design.abstractfactory.vehicle.Car;
import com.design.abstractfactory.vehicle.Truck;
import com.design.abstractfactory.vehicle.Van;
import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is the factory used to retrieve a vehicle.
 * 
 * @author Jonathan Ware
 */
public class VehicleFactory extends AbstractFactory {
	
	/**
	 * {@inheritDoc}
	 */
	public Color getColor(String colorType)
	{
		return null;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public Vehicle getVehicle(String vehicleType)
	{
		if("car".equalsIgnoreCase(vehicleType))
		{
			return new Car();
		}
		
		if("truck".equalsIgnoreCase(vehicleType))
		{
			return new Truck();
		}
		
		if("van".equalsIgnoreCase(vehicleType))
		{
			return new Van();
		}

		throw new IllegalArgumentException("No such vehicle type");
	}
}
