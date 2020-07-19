package com.coffeeMachine.model;

import com.coffeeMachine.model.supply.Beverage;
import com.coffeeMachine.model.supply.factory.BeverageFactory;

public class Outlet{
	String outletNumber;
	private CoffeeMachine coffeeMachine;
	
	//Outlet is the machine outlet with the outleNumber and the reference to Coffee machine it belongs to.
	public Outlet(String outletNumber, CoffeeMachine machine) {
		this.coffeeMachine = machine;
		this.outletNumber = outletNumber;
	}
	
	//This method figures out which beverage to dispense, and calls the Dispenser method.
	public Beverage getBeverage(String type) {
		Beverage beverage = BeverageFactory.createBeverage(type);
		Dispenser d1 = new Dispenser(this, beverage, this.coffeeMachine);
		d1.start();
		//this start method creates a separate thread to update the coffee machine and calls its method to dispense beverage
		return beverage;
	}

	public String getOutletNumber() {
		return outletNumber;
	}
	
}
