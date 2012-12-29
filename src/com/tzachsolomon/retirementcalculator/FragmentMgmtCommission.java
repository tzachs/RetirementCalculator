package com.tzachsolomon.retirementcalculator;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentMgmtCommission extends SherlockFragment implements
		OnClickListener {

	private Button buttonHelpMgmCommission;
	private Button buttonCalcMgmtCommission;
	private EditText editTextInitSum;
	private EditText editTextCommissionGroath;
	private EditText editTextCommissionMonth;
	private EditText editTextCommissionSum;
	private EditText editTextMonthPay;

	private EditText editTextYearsToRetirement;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		//
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		//
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//
		View view = inflater.inflate(R.layout.fragment_mgmt_commission,
				container, false);

		initializeVariables(view);

		return view;

	}

	private void initializeVariables(View view) {
		//
		buttonHelpMgmCommission = (Button) view
				.findViewById(R.id.buttonHelpMgmCommission);
		buttonCalcMgmtCommission = (Button) view
				.findViewById(R.id.buttonCalcMgmtCommission);

		editTextCommissionGroath = (EditText) view
				.findViewById(R.id.editTextCommissionGroath);
		editTextCommissionMonth = (EditText) view
				.findViewById(R.id.editTextCommissionMonth);
		editTextCommissionSum = (EditText) view
				.findViewById(R.id.editTextCommissionSum);
		editTextInitSum = (EditText) view.findViewById(R.id.editTextInitSum);
		editTextMonthPay = (EditText) view.findViewById(R.id.editTextMonthPay);
		editTextYearsToRetirement = (EditText) view
				.findViewById(R.id.editTextYearsToRetirement);


		buttonHelpMgmCommission.setOnClickListener(this);
		buttonCalcMgmtCommission.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		//
		switch (v.getId()) {
		case R.id.buttonHelpMgmCommission:
			buttonHelpMgmtCommission_Clicked();
			break;
		case R.id.buttonCalcMgmtCommission:
			buttonCalcMgmtCommission_Clicked();
			break;
		}

	}

	private void buttonHelpMgmtCommission_Clicked() {
		//
		FragmentManager manager = getSherlockActivity()
				.getSupportFragmentManager();
		FragmentHelp help = new FragmentHelp();
		Bundle args = new Bundle();
		args.putInt("string_resource", R.string.help_mgmt_commission);
		help.setArguments(args);
		help.show(manager, "fragment_commission_help");
	}

	private void buttonCalcMgmtCommission_Clicked() {
		//
		try {
			calculate();
		} catch (Exception e) {
			verify();
		}

	}

	private void verify() {
		//
		StringBuilder sb = new StringBuilder();
		sb.append("אנא השלם ערך ");
		if (editTextCommissionGroath.getText().length() == 0) {
			sb.append(editTextCommissionGroath.getHint());
		} else if (editTextCommissionMonth.getText().length() == 0) {
			sb.append(editTextCommissionMonth.getHint());
		} else if (editTextCommissionSum.getText().length() == 0) {
			sb.append(editTextCommissionSum.getHint());
		} else if (editTextInitSum.getText().length() == 0) {
			sb.append(editTextInitSum.getHint());
		} else if (editTextMonthPay.getText().length() == 0) {
			sb.append(editTextMonthPay.getHint());
		} else if (editTextYearsToRetirement.getText().length() == 0) {
			sb.append(editTextYearsToRetirement.getHint());
		}

		Toast.makeText(getSherlockActivity(), sb.toString(), Toast.LENGTH_LONG)
				.show();

	}

	private void calculate() {

		//
		double initSum = Double.parseDouble(editTextInitSum.getText()
				.toString());
		double monthPay = Double.parseDouble(editTextMonthPay.getText()
				.toString());
		double commissionMonth = Double.parseDouble(editTextCommissionMonth
				.getText().toString());
		double commissionSum = Double.parseDouble(editTextCommissionSum
				.getText().toString());
		double commissionGroath = Double.parseDouble(editTextCommissionGroath
				.getText().toString());

		int yearsToRetirment = Integer.parseInt(editTextYearsToRetirement
				.getText().toString());

		Calc.calc(initSum, monthPay, commissionMonth, commissionSum,
				yearsToRetirment, commissionGroath);

		String sumFormat = NumberFormat.getInstance().format(Calc.sumAfterCommissions);
		String commissionChargedFormat = NumberFormat.getInstance().format(
				Calc.commission);
		String percentString = NumberFormat.getInstance().format(
				Calc.commission * 100 / Calc.sumAfterCommissions);
		
		FragmentManager manager = getSherlockActivity()
				.getSupportFragmentManager();
		
		FragmentMgmtCommissionResult result = new FragmentMgmtCommissionResult();
		
		Bundle args = new Bundle();
		args.putString("sumFormat", sumFormat);
		args.putString("commissionChargedFormat", commissionChargedFormat);
		args.putString("percentString", percentString);
		
		
		result.setArguments(args);
		result.show(manager, "fragment_commission_help");
		
		

		

	}

}
