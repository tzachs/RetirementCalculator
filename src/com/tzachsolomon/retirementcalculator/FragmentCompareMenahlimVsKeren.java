package com.tzachsolomon.retirementcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentCompareMenahlimVsKeren extends SherlockFragment implements
		OnFocusChangeListener, OnClickListener {

	private EditText editTextMenahilmMekadem;
	private EditText editTextMonthCommissionKeren;
	private EditText editTextMonthCommissionMenahilm;
	private EditText editTextMonthSaving;
	private EditText editTextYearCommissionKeren;
	private EditText editTextYearCommissionMenahilm;
	private EditText editTextNumberOfYears;
	private TextView textViewPercentKeren;
	private TextView textViewPercentMenahilm;
	private EditText editTextPercentKeren;
	private EditText editTextPercentMenahilm;
	private Button buttonCalcMenahlimVsKeren;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//
		View view = inflater.inflate(R.layout.fragment_menahlim_vs_keren, null);

		initializeVariables(view);

		return view;
	}

	private void initializeVariables(View view) {
		//
		editTextMonthSaving = (EditText) view
				.findViewById(R.id.editTextMonthSaving);

		editTextPercentKeren = (EditText) view
				.findViewById(R.id.editTextPercentKeren);
		editTextPercentMenahilm = (EditText) view
				.findViewById(R.id.editTextPercentMenahilm);

		editTextPercentKeren.setOnFocusChangeListener(this);
		editTextPercentMenahilm.setOnFocusChangeListener(this);
		editTextMonthSaving.setOnFocusChangeListener(this);

		editTextMenahilmMekadem = (EditText) view
				.findViewById(R.id.editTextMenahilmMekadem);
		editTextMonthCommissionKeren = (EditText) view
				.findViewById(R.id.editTextMonthCommissionKeren);
		editTextMonthCommissionMenahilm = (EditText) view
				.findViewById(R.id.editTextMonthCommissionMenahilm);

		editTextYearCommissionKeren = (EditText) view
				.findViewById(R.id.editTextYearCommissionKeren);
		editTextYearCommissionMenahilm = (EditText) view
				.findViewById(R.id.editTextYearCommissionMenahilm);
		editTextNumberOfYears = (EditText) view
				.findViewById(R.id.editTextNumberOfYears);

		textViewPercentKeren = (TextView) view
				.findViewById(R.id.textViewPercentKeren);
		textViewPercentMenahilm = (TextView) view
				.findViewById(R.id.textViewPercentMenahilm);

		buttonCalcMenahlimVsKeren = (Button) view
				.findViewById(R.id.buttonCalcMenahlimVsKeren);
		buttonCalcMenahlimVsKeren.setOnClickListener(this);

	}

	private void calcTextViewPerecent(TextView textView, EditText editText) {

		try {
			double percent = Double.parseDouble(editText.getText().toString());
			double from = Double.parseDouble(editTextMonthSaving.getText()
					.toString());

			percent *= 0.01 * from;
			textView.setText("" + percent);

		} catch (NumberFormatException e) {
			textView.setText("0");

		}

	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		//
		switch (v.getId()) {
		case R.id.editTextPercentKeren:
			if (!hasFocus) {

				calcTextViewPerecent(textViewPercentKeren, editTextPercentKeren);

			}

			break;
		case R.id.editTextPercentMenahilm:
			if (!hasFocus) {
				calcTextViewPerecent(textViewPercentMenahilm,
						editTextPercentMenahilm);

			}

		case R.id.editTextMonthSaving:
			if (!hasFocus) {
				calcTextViewPerecent(textViewPercentKeren, editTextPercentKeren);
				calcTextViewPerecent(textViewPercentMenahilm,
						editTextPercentMenahilm);

			}

			break;

		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		//
		switch (v.getId()) {
		case R.id.buttonCalcMenahlimVsKeren:
			buttonCalcMenahlimVsKeren_Clicked();
			break;
		}

	}

	private void buttonCalcMenahlimVsKeren_Clicked() {
		//
		double sumMenahlim;
		double commissionMenahlim;
		double sumKeren;
		double commissionKeren;
		double monthSaving;
		double monthCommission;
		double yearCommission;
		double mekadem;
		int yearsToRetirment;

		calcTextViewPerecent(textViewPercentKeren, editTextPercentKeren);
		calcTextViewPerecent(textViewPercentMenahilm, editTextPercentMenahilm);

		monthSaving = Double.parseDouble(textViewPercentKeren.getText()
				.toString());
		monthCommission = Double.parseDouble(editTextMonthCommissionKeren
				.getText().toString());
		yearCommission = Double.parseDouble(editTextYearCommissionKeren
				.getText().toString());
		yearsToRetirment = Integer.parseInt(editTextNumberOfYears.getText()
				.toString());

		Calc.calc(0, monthSaving, monthCommission, yearCommission,
				yearsToRetirment, 4);

		sumKeren = Calc.sumAfterCommissions;
		commissionKeren = Calc.commission;

		monthSaving = Double.parseDouble(textViewPercentMenahilm.getText()
				.toString());
		monthCommission = Double.parseDouble(editTextMonthCommissionMenahilm
				.getText().toString());

		yearCommission = Double.parseDouble(editTextYearCommissionMenahilm
				.getText().toString());

		Calc.calc(0, monthSaving, monthCommission, yearCommission,
				yearsToRetirment, 0);
		
		mekadem = Double.parseDouble(editTextMenahilmMekadem.getText().toString());

		sumMenahlim = Calc.sumAfterCommissions;
		commissionMenahlim = Calc.commission;
		
		double hefresh = sumKeren - sumMenahlim;
		
		double menahlimPension = sumMenahlim / mekadem;
		double mekademKeren = sumKeren / menahlimPension;
		
		StringBuilder sb  = new StringBuilder();
		
		sb.append("You pay ");
		sb.append(hefresh);
		sb.append(" insurance so that mekadem won't be ");
		sb.append(mekademKeren);
		
		Toast.makeText(getSherlockActivity(),sb.toString(),Toast.LENGTH_LONG).show();
				

		Toast.makeText(getSherlockActivity(),
				"" + sumKeren + " vs. " + sumMenahlim, Toast.LENGTH_LONG)
				.show();

	}
}
