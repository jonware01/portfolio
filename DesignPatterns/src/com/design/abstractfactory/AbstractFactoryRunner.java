package com.design.abstractfactory;

import com.design.abstractfactory.color.Color;
import com.design.abstractfactory.vehicle.Vehicle;

/**
 * This class is the runner for the Abstract Factory design.
 * 
 * @author Jonathan Ware
 */
public class AbstractFactoryRunner {
	
	/**
	 * Runner method.
	 * @param args command line arguments
	 */
	public static void main(String[] args)
	{
		// Get the vehicle factory
		AbstractFactory vehicleFactory = FactoryProducer.getFactory("vehicle");
		
		// Get the color factory
		AbstractFactory colorFactory = FactoryProducer.getFactory("color");
		
		// Get a car
		Vehicle car = vehicleFactory.getVehicle("car");
		car.manufacture();
		
		// Get red
		Color red = colorFactory.getColor("red");
		red.paint();
		
		// Get a truck
		Vehicle truck = vehicleFactory.getVehicle("truck");
		truck.manufacture();
		
		// Get green
		Color green = colorFactory.getColor("green");
		green.paint();
		
		// Get a van
		Vehicle van = vehicleFactory.getVehicle("van");
		van.manufacture();
		
		// Get blue
		Color blue = colorFactory.getColor("blue");
		blue.paint();
	}
}
