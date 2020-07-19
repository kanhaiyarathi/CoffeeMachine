package com.coffeeMachine.model.supply.factory;

import com.coffeeMachine.database.Database;
import com.coffeeMachine.model.supply.Ingredient;

public class IngredientFactory {
	public static Ingredient createIngredient(String type) throws IllegalArgumentException{
		Ingredient ingredient;
		switch (type.toLowerCase())
        {
			case "green_mixture":
				ingredient = Database.getInstance().getGreenMixture();
				break;
			case "hot_milk":
				ingredient = Database.getInstance().getHotMilk();
				break;
			case "hot_water":
				ingredient = Database.getInstance().getHotWater();
				break;
			case "tea_leave_syrup":
				ingredient = Database.getInstance().getTeaLeaveSyrup();
				break;
			case "sugar_syrup":
				ingredient = Database.getInstance().getSugarSyrup();
				break;
			case "ginger_syrup":
				ingredient = Database.getInstance().getGingerSyrup();
				break;
			default: throw new IllegalArgumentException("No such Ingredient");				
        }
		return ingredient;
		
	}	
}
