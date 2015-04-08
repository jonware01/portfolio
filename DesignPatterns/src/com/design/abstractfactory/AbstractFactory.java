package com.design.abstractfactory;

import com.design.abstractfactory.color.Color;
import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is responsible for retrieving factories
 * 
 * @author Jonathan Ware
 */
public abstract class AbstractFactory {
	
	/**
	 * Retrieves a color of the specified type.
	 * 
	 * @param colorType Color type to retrieve 
	 * @return Color of the type specified
	 */
	abstract Color getColor(String colorType);
	
	/**
	 * Retrieves a vehicle of the specified type.
	 * 
	 * @param vehicleType Vehicle type to retrieve
	 * @return Vehicle of the type specified
	 */
	abstract Vehicle getVehicle(String vehicleType);
}
