package com.tzachsolomon.retirementcalculator;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentHowDivideMenahlimKeren extends SherlockFragment implements
		OnClickListener {

	private EditText editTextMekademMenahilm;
	private EditText editTextMekademKeren;
	private EditText editTextMonthCommissionKeren;
	private EditText editTextMonthCommissionMenahilm;
	private EditText editTextMonthSaving;
	private EditText editTextYearCommissionKeren;
	private EditText editTextYearCommissionMenahilm;
	private EditText editTextNumberOfYears;

	private Button buttonCalcMenahlimVsKeren;
	private Button buttonHelpMenahalimVsKeren;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//
		View view = inflater.inflate(
				R.layout.fragment_how_divide_keren_menahlim, null);

		initializeVariables(view);

		return view;
	}

	private void initializeVariables(View view) {
		//
		editTextMonthSaving = (EditText) view
				.findViewById(R.id.editTextMonthSaving);

		editTextMekademMenahilm = (EditText) view
				.findViewById(R.id.editTextMekademMenahilm);
		editTextMekademKeren = (EditText) view
				.findViewById(R.id.editTextMekademKeren);
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

		buttonCalcMenahlimVsKeren = (Button) view
				.findViewById(R.id.buttonCalcMenahlimVsKeren);
		buttonCalcMenahlimVsKeren.setOnClickListener(this);

		buttonHelpMenahalimVsKeren = (Button) view
				.findViewById(R.id.buttonHelpMenahalimVsKeren);
		buttonHelpMenahalimVsKeren.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		//
		switch (v.getId()) {
		case R.id.buttonHelpMenahalimVsKeren:
			buttonHelpMenahalimVsKeren_Clicked();
			break;

		case R.id.buttonCalcMenahlimVsKeren:
			buttonCalcMenahlimVsKeren_Clicked();
			break;
		}

	}

	private void buttonHelpMenahalimVsKeren_Clicked() {
		//
		FragmentManager manager = getSherlockActivity()
				.getSupportFragmentManager();
		FragmentHelp help = new FragmentHelp();
		Bundle args = new Bundle();
		args.putInt("string_resource", R.string.help_how_to_divide);
		help.setArguments(args);
		help.show(manager, "fragment_commission_help");

	}

	private void buttonCalcMenahlimVsKeren_Clicked() {
		//

		try {
			calcHowToDivide();
		} catch (NumberFormatException e) {
			verify();
		}

	}

	private void verify() {
		//
		StringBuilder sb = new StringBuilder();
		sb.append("אנא השלם ערך ");
		if (editTextMonthSaving.getText().length() == 0) {
			sb.append(editTextMonthSaving.getHint());
		} else if (editTextMekademKeren.getText().length() == 0) {
			sb.append(editTextMekademKeren.getHint());
		} else if (editTextMekademMenahilm.getText().length() == 0) {
			sb.append(editTextMekademMenahilm.getHint());
		} else if (editTextMonthCommissionKeren.getText().length() == 0) {
			sb.append(editTextMonthCommissionKeren.getHint());
		} else if (editTextYearCommissionKeren.getText().length() == 0) {
			sb.append(editTextYearCommissionKeren.getHint());
		} else if (editTextMonthCommissionMenahilm.getText().length() == 0) {
			sb.append(editTextMonthCommissionMenahilm.getHint());
		} else if (editTextYearCommissionMenahilm.getText().length() == 0) {
			sb.append(editTextYearCommissionMenahilm.getHint());
		} else if (editTextNumberOfYears.getText().length() == 0) {
			sb.append(editTextNumberOfYears.getHint());
		}

		Toast.makeText(getSherlockActivity(), sb.toString(), Toast.LENGTH_LONG)
				.show();

	}

	private void calcHowToDivide() {
		double monthSaving;
		double monthCommissionKeren;
		double monthCommissionMenahalim;
		double yearCommissionKeren;
		double yearCommissionMenahalim;
		double mekademKeren;
		double mekademMenahlim;
		int yearsToRetirment;
		double i;
		ArrayList<CalcBundle> calcType = new ArrayList<CalcBundle>();

		monthSaving = Double.parseDouble(editTextMonthSaving.getText()
				.toString());
		mekademKeren = Double.parseDouble(editTextMekademKeren.getText()
				.toString());
		mekademMenahlim = Double.parseDouble(editTextMekademMenahilm.getText()
				.toString());
		monthCommissionKeren = Double.parseDouble(editTextMonthCommissionKeren
				.getText().toString());
		yearCommissionKeren = Double.parseDouble(editTextYearCommissionKeren
				.getText().toString());
		monthCommissionMenahalim = Double
				.parseDouble(editTextMonthCommissionMenahilm.getText()
						.toString());
		yearCommissionMenahalim = Double
				.parseDouble(editTextYearCommissionMenahilm.getText()
						.toString());
		yearsToRetirment = Integer.parseInt(editTextNumberOfYears.getText()
				.toString());

		for (i = 0; i < 101; i += 10) {
			CalcBundle a = new CalcBundle();
			Calc.calc(0, monthSaving * (i / 100), monthCommissionKeren,
					yearCommissionKeren, yearsToRetirment, 4);
			a.percentKeren = i;
			a.sumKeren = Calc.sumAfterCommissions;
			Calc.calc(0, monthSaving * ((100 - i) / 100),
					monthCommissionMenahalim, yearCommissionMenahalim,
					yearsToRetirment, 4);
			a.percentMenahlim = (100 - i);
			a.sumMenahlim = Calc.sumAfterCommissions;
			a.mekademKeren = mekademKeren;
			a.mekademMenahlim = mekademMenahlim;
			calcType.add(a);
		}

		FragmentManager manager = getSherlockActivity()
				.getSupportFragmentManager();
		FragmentHowDivideMenahlimKerenResult result = new FragmentHowDivideMenahlimKerenResult();

		Bundle bundle = new Bundle();
		bundle.putSerializable("ser", calcType);
		result.setArguments(bundle);
		result.show(manager, "fragment_how_divide_result");

	}

}
