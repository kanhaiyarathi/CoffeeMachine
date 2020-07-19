package com.coffeeMachine.model.supply.factory;

import com.coffeeMachine.database.Database;
import com.coffeeMachine.model.supply.Beverage;

//this is a berage factory which returns the beverage object from the database based on the input it gets from the front UI.
//that is, based on simulator input it returns the object after reading it from database. 
public class BeverageFactory {
	public static Beverage createBeverage(String type) throws IllegalArgumentException{
		Beverage beverage;
		switch (type.toLowerCase())
        {
			case "hot_tea":
				beverage = Database.getInstance().getHotTea();
				break;
			case "green_tea":
				beverage = Database.getInstance().getGreenTea();
				break;
			default: throw new IllegalArgumentException("No such Beverage");				
        }
		return beverage;
		
	}
}
