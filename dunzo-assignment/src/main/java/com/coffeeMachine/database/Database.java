package com.coffeeMachine.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.coffeeMachine.model.CoffeeMachine;
import com.coffeeMachine.model.supply.Beverage;
import com.coffeeMachine.model.supply.Ingredient;
import com.coffeeMachine.model.supply.Portion;

//Making this Database a singleton Class.
//This class is used to read the data from Database. I have created different methods to read the data.

public class Database {

	private static Database data = null;
	
	Map<String, Ingredient> ingredients;
	
	private Database() {
		readIngredients();
	}
	
	//skipping double check singleton, just for simple code
	public static Database getInstance() {
		if(data==null)
			data = new Database();
		return data;
	}
	
	//this is called when the database instance is created, just to keep the ingredients object in a map
	private void readIngredients() {
		this.ingredients = new HashMap<String, Ingredient>(){
			{
				put("hotWater", new Ingredient("hot_water"));
				put("hotMilk", new Ingredient("hot_milk"));
				put("gingerSyrup", new Ingredient("ginger_syrup"));
				put("sugarSyrup", new Ingredient("sugar_syrup"));
				put("teaLeaveSyrup", new Ingredient("tea_leaves_syrup"));
				put("greenMixture", new Ingredient("green_mixture"));
			}
		};
	}
	

	//this method is called by the simulator, when the coffeMachine is instantiated
	public void setCoffeeMachineInitialIngredients(CoffeeMachine cm) {
		cm.setQuantityAvailable(new HashMap<Ingredient, Integer>(){{
			put(ingredients.get("hotWater"), 600);
			put(ingredients.get("hotMilk"), 600);
			put(ingredients.get("gingerSyrup"), 600);
			put(ingredients.get("sugarSyrup"), 600);
			put(ingredients.get("teaLeaveSyrup"), 600);
			put(ingredients.get("greenMixture"), 20);
		}});
	}
	
	//method to return a hotTea object from database;
	public Beverage getHotTea(){
		Beverage hotTea = new Beverage("hot_tea", new ArrayList<Portion>() {{
				add(new Portion(ingredients.get("hotWater"), 200));
				add(new Portion(ingredients.get("hotMilk"), 100));
				add(new Portion(ingredients.get("gingerSyrup"), 10));
				add(new Portion(ingredients.get("sugarSyrup"), 10));
				add(new Portion(ingredients.get("teaLeaveSyrup"), 30));
		}});
		return hotTea;  
	}
	
	//method to return a greenTea object from database;
	public Beverage getGreenTea(){
		Beverage hotTea = new Beverage("green_tea", new ArrayList<Portion>() {{
				add(new Portion(ingredients.get("hotWater"), 100));
				add(new Portion(ingredients.get("gingerSyrup"), 30));
				add(new Portion(ingredients.get("sugarSyrup"), 50));
				add(new Portion(ingredients.get("greenMixture"), 30));
		}});
		return hotTea;  
	}
	
	//need to return many sich ingredients
	public Ingredient getGreenMixture() {
		return ingredients.get("greenMixture");
	}
	
	public Ingredient getHotWater() {
		return ingredients.get("hotWater");
	}
	
	public Ingredient getGingerSyrup() {
		return ingredients.get("gingerSyrup");
	}
	
	public Ingredient getSugarSyrup() {
		return ingredients.get("sugarSyrup");
	}
	
	public Ingredient getHotMilk() {
		return ingredients.get("hotMilk");
	}
	
	public Ingredient getTeaLeaveSyrup() {
		return ingredients.get("teaLeaveSyrup");
	}
	
	
}
