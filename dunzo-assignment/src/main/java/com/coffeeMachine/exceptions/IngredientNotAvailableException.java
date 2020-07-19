package com.coffeeMachine.exceptions;

import com.coffeeMachine.model.supply.Ingredient;
//Exception to throw in case enough ingredient is not available
public class IngredientNotAvailableException extends Exception{
	private final Ingredient ingredient;
	public IngredientNotAvailableException(String message, Ingredient ingredient) {
		super(message);
		this.ingredient = ingredient;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
}
