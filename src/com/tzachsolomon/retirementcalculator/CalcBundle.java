package com.tzachsolomon.retirementcalculator;

import java.text.NumberFormat;

public class CalcBundle {
	double sumKeren;
	double sumMenahlim;
	double percentKeren;
	double percentMenahlim;
	double mekademMenahlim;
	double mekademKeren;

	public String toStringPensionSalary() {
		double total = (sumKeren / mekademKeren)
				+ (sumMenahlim / mekademMenahlim);
		return "" + NumberFormat.getInstance().format(total);
	}

	public String toStringTotal() {
		double total = sumKeren + sumMenahlim;
		return "" + NumberFormat.getInstance().format(total);
	}

	public String toStringKeren() {
		return "" + percentKeren + "%" + "\t"
				+ NumberFormat.getInstance().format(sumKeren);

	}

	public String toStringPercentKeren() {
		return percentKeren + "%";

	}

	public String toStringPercentMenahalim() {
		return percentMenahlim + "%";

	}

	public String toStringMenahalim() {
		return "" + percentMenahlim + "%" + "\t"
				+ NumberFormat.getInstance().format(sumMenahlim);

	}

}
