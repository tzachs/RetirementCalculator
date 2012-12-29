package com.tzachsolomon.retirementcalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class FragmentMgmtCommissionResult extends SherlockDialogFragment {
	private TextView textViewComissionSum;
	private TextView textViewComissionSumPercent;
	private TextView textViewComissionTotalSum;
	private  String sumFormat,commissionChargedFormat,percentString;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 
		super.onCreate(savedInstanceState);
		
		
		sumFormat = getArguments().getString("sumFormat");
		commissionChargedFormat = getArguments().getString("commissionChargedFormat");
		percentString = getArguments().getString("percentString");
		
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 
		View view = inflater.inflate(R.layout.fragment_mgmt_commission_result, null);
		
		initializeVariables(view);
		
		return view;
	}

	private void initializeVariables(View view) {
		// 

		textViewComissionSum = (TextView) view
				.findViewById(R.id.textViewComissionSum);
		textViewComissionSumPercent = (TextView) view
				.findViewById(R.id.textViewComissionSumPercent);
		textViewComissionTotalSum = (TextView) view
				.findViewById(R.id.textViewComissionTotalSum);
		
		textViewComissionTotalSum.setText("סכום לפרישה " + sumFormat);
		textViewComissionSum.setText("סך דמי ניהול שנגבו "
				+ commissionChargedFormat);
		textViewComissionSumPercent.setText("אחוז דמי ניהול מסך כספים שנצברו "
				+ percentString);
		
	}

}
