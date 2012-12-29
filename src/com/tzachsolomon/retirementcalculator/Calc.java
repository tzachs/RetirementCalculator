package com.tzachsolomon.retirementcalculator;


public  class  Calc {

	public static double sumAfterCommissions;
	public static double commission;
	
	public static void calc (double initSum, double monthSaving, double monthCommission, double yearCommission, int yearsToRetirment, double groath ){
		
		double monthSavingNetoInAYear = monthSaving * monthCommission * 0.12;
		double yearSavingNeto = 12 *  monthSaving - monthSavingNetoInAYear;
		
		sumAfterCommissions = 0;
		commission = 0;
		
		yearCommission *= 0.01;
		
		groath = (groath * 0.01) + 1;
		
		// adding first sum
		sumAfterCommissions += initSum;

		while (yearsToRetirment > 0){
			
			// add neto savings
			sumAfterCommissions += yearSavingNeto;
			// add the groath 
			sumAfterCommissions *= groath;
			
			// subtract year commission
			sumAfterCommissions = sumAfterCommissions - ( sumAfterCommissions * yearCommission);
			
			commission += monthSavingNetoInAYear;
			commission += sumAfterCommissions * yearCommission;
			
			yearsToRetirment--;
		}
		
		
		
		

	}
	
}
