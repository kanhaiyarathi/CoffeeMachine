package com.coffeeMachine.model;

import com.coffeeMachine.model.supply.Beverage;

public class Dispenser implements Runnable{
	private Thread t;
	Outlet outlet;
	Beverage beverage;
	CoffeeMachine coffeeMachine;
	public Dispenser(Outlet outlet, Beverage beverage, CoffeeMachine coffeeMachine) {
		this.beverage = beverage;
		this.outlet = outlet;
		this.coffeeMachine = coffeeMachine;
	}

	@Override
	public void run() {
	
		//calls the coffeemachine dispense beverage if possible. If no exception then its a success and thread can move ahead.
		boolean isSuccess = this.coffeeMachine.dispenseBeverageIfPossible(beverage);
		
		
		if(isSuccess) {
			//this thread sleep is just added to add some time period for a thread while dispensing
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Dispensed from outlet:" + this.outlet.getOutletNumber() + "  and beverage:" + this.beverage.getName() );
		}			 
		else
			System.out.println("Could not dispense from outlet:" + this.outlet.getOutletNumber() + "  and beverage:" + this.beverage.getName() );
	}
	
   public void start () {
	      System.out.println("Starting dispensing from outlet:" + this.outlet.getOutletNumber() + "  and beverage:" + this.beverage.getName() );
	      
	      if (t == null) {
	         t = new Thread (this, "dispenser");
	         t.start ();
	      }
	   }
}
