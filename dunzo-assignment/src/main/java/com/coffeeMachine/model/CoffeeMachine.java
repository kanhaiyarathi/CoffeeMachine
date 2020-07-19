package com.coffeeMachine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.coffeeMachine.exceptions.IngredientNotAvailableException;
import com.coffeeMachine.exceptions.NoSuchIngredientFound;
import com.coffeeMachine.model.supply.Beverage;
import com.coffeeMachine.model.supply.Ingredient;
import com.coffeeMachine.model.supply.Portion;
import com.coffeeMachine.model.supply.factory.IngredientFactory;

public class CoffeeMachine {
	
	Map<Ingredient, Integer> quantityAvailable;
	List<Outlet> outlets;
	
	//Coffee Machine consists of Number of Outlets and the initial map of Quantity Available, stating the initial ingredients available
	public CoffeeMachine(int numberOfOutlets) {
		outlets = new ArrayList<Outlet>(numberOfOutlets);
		for(int i=0;i<numberOfOutlets; i++)
			outlets.add(new Outlet("outlet number:" + i , this));
	}
	
	//base on which outlet is pressed, this method is called; each outlet provides option of getBeverage with input as the type
	public Beverage dispenseArrayFromOutlet(int num, String type) {
		return outlets.get(num).getBeverage(type);
	}
	
	//this is a thread safe method as it updates the common map of quantities in the coffee machine
	//public method
	public boolean dispenseBeverageIfPossible(Beverage beverage){
		try {
			//getting lock on quantity available map
			synchronized(this.quantityAvailable) {
				//checks if all the ingredients are present in required beverage;
				if(canDispenseBeverage(beverage)) {
					dispenseBeverage(beverage);		//if yes, then update the coffee machine ingredients 
				}				
			}
			return true;
		}catch(NoSuchIngredientFound e) {
			System.out.println(e.getIngredient().getName() + ":"+ e.getMessage() +":"+beverage.getName());
		}catch(IngredientNotAvailableException e) {
			System.out.println(e.getIngredient().getName() +":"+e.getMessage()+":"+beverage.getName());
		}
		return false;
	}
	
	//checks if all the ingredients are present in the required beverage and throw exception if 
	//the ingredient is not there or sufficient quantity is not there
	//not a public method 
	private boolean canDispenseBeverage(Beverage beverage) throws NoSuchIngredientFound, IngredientNotAvailableException{
		ArrayList<Portion> portionsRequired = beverage.getPortions();
		for(Portion p: portionsRequired) {
			Ingredient ingredient = p.getIngredient();
			if(!quantityAvailable.containsKey(ingredient))
				throw new NoSuchIngredientFound("Ingredient Not Found", ingredient);
			if(quantityAvailable.get(ingredient) < p.getQuantity())
				throw new IngredientNotAvailableException("Not Enough Ingredient is Available", ingredient);
		}
		return true;
	}
	
	//updates the quantity available map of coffee machine
	private boolean dispenseBeverage(Beverage beverage) {
		ArrayList<Portion> portionsRequired = beverage.getPortions();
		
		for(Portion p: portionsRequired) {
			Ingredient ingredient = p.getIngredient();
			quantityAvailable.put(ingredient, quantityAvailable.get(ingredient) - p.getQuantity());
		}
		return true;
	}
	
	public boolean updateMachineInventory(String type, int qty) {
		//get ingredienet from the database based on type
		Ingredient ingredient = IngredientFactory.createIngredient(type);
		try {
			//get lock on quantity available
			synchronized(this.quantityAvailable) {
				updateMachineInventory(ingredient, qty);
			}
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	//this method is called by the simulator in case the invetory of a particular ingredient needs to be updated
	private boolean updateMachineInventory(Ingredient ingredient, int qty) throws NoSuchIngredientFound {
		if(!quantityAvailable.containsKey(ingredient))
			throw new NoSuchIngredientFound("Could not update the ingrediet as it is not present", ingredient);
		quantityAvailable.put(ingredient,qty);
		return true;
	}

	public void setQuantityAvailable(Map<Ingredient, Integer> quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}
	
	//this method is to state the warning message in case the ingredient level goes by 30 ml.
	//needs to called after a particular time of interval to check the status
	public void stateTheIngredientStatus() {
		for(Map.Entry<Ingredient, Integer> e : quantityAvailable.entrySet()) {
			if(e.getValue() < 30) {
				System.out.println("Warning:" + e.getKey().getName() +"  level low:" + e.getValue() + " ml ");
			}
		}
	}
	
}
