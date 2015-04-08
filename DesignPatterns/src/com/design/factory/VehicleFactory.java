package com.design.factory;

import com.design.abstractfactory.vehicle.Car;
import com.design.abstractfactory.vehicle.Truck;
import com.design.abstractfactory.vehicle.Van;
import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is the factory used to retrieve a vehicle.
 * 
 * @author Jonathan Ware
 */
public class VehicleFactory {
	
	/**
	 * Retrieves a vehicle of the specified type.
	 * <p>
	 * This implementation is a Static Factory implementation
	 * which saves possible multiple factory creation.
	 * 
	 * @param vehicleType Vehicle type to retrieve
	 * @return Vehicle of the type specified
	 */
	public static Vehicle getVehicle(String vehicleType)
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
