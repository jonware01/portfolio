package com.design.factory;

import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is the runner for the Factory design.
 * 
 * @author Jonathan Ware
 */
public class FactoryRunner {

	/**
	 * Runner method.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {		
		// Get a car
		Vehicle car = VehicleFactory.getVehicle("car");
		car.manufacture();
		
		// Get a truck
		Vehicle truck = VehicleFactory.getVehicle("truck");
		truck.manufacture();
		
		// Get a van
		Vehicle van = VehicleFactory.getVehicle("van");
		van.manufacture();
	}

}
