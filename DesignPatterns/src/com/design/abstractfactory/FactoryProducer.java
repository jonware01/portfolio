package com.design.abstractfactory;

/**
 * This class is the factory used to retrieve the correct factory
 * 
 * @author Jonathan Ware
 */
public class FactoryProducer {
	
	/**
	 * Retrieves a factory of the specified type.
	 * 
	 * @param factoryType Factory type to retrieve 
	 * @return Factory of the type specified
	 */
	public static AbstractFactory getFactory(String factoryType)
	{
		if("color".equalsIgnoreCase(factoryType))
		{
			return new ColorFactory();
		}
		
		if("vehicle".equalsIgnoreCase(factoryType))
		{
			return new VehicleFactory();
		}
		
		throw new IllegalArgumentException("No such factory");
	}
}
