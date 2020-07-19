package com.coffeeMachine.simulator;

import com.coffeeMachine.database.Database;
import com.coffeeMachine.model.CoffeeMachine;

public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int outlets = 5;
		//creating coffee machine with number of outlets.
		CoffeeMachine cm = new CoffeeMachine(outlets);
		
		//reading from database for initial readings of ingredinets in coffe machine 
		Database.getInstance().setCoffeeMachineInitialIngredients(cm);
		
		//calling these methods based on the outlet number and the type of beverage passed
		cm.dispenseArrayFromOutlet(2, "hot_tea");
		cm.dispenseArrayFromOutlet(3, "green_tea");
		
		//this method is called to check for the status of ingredients. should be regularly called after fix interval of time
		cm.stateTheIngredientStatus();
		cm.updateMachineInventory("green_mixture",50);
		cm.dispenseArrayFromOutlet(3, "green_tea");
		
	}
}
