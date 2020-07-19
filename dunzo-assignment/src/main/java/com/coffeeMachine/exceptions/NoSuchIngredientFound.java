package com.coffeeMachine.exceptions;

import com.coffeeMachine.model.supply.Ingredient;

public class NoSuchIngredientFound extends Exception{
	private final Ingredient ingredient;
	public NoSuchIngredientFound(String message, Ingredient ingredient) {
		super(message);
		this.ingredient = ingredient;
	}
	
	public Ingredient getIngredient() {
		return ingredient;
	}
	
}
