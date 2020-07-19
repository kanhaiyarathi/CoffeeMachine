package com.coffeeMachine.model.supply;


//portion is basically ingredient and the quatity for that ingredient.
public class Portion {
	private Ingredient ingredient;
	private int quantity;
	public Portion(Ingredient i, Integer qty) {
		this.setIngredient(i);
		this.setQuantity(qty);
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
