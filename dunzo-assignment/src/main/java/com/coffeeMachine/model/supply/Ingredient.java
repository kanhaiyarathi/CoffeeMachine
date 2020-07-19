package com.coffeeMachine.model.supply;

//just an ingredient object. It does not have any association with quantity. 
public class Ingredient {
	private String name;
	
	public Ingredient(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
