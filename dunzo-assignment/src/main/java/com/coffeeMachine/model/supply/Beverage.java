package com.coffeeMachine.model.supply;

import java.util.ArrayList;

//Class Beverage consists of a list of Portion.
//Portion is defined based on the ingredient and its quantity required.
public class Beverage {
	String name;
	ArrayList<Portion> portions;
	public Beverage(String name, ArrayList<Portion> portions) {
		this.name = name;
		this.portions = portions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Portion> getPortions() {
		return portions;
	}
	public void setPortions(ArrayList<Portion> portions) {
		this.portions = portions;
	}
}
