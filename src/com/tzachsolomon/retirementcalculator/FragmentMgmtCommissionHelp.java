package com.tzachsolomon.retirementcalculator;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;

public class FragmentMgmtCommissionHelp extends SherlockDialogFragment implements OnClickListener {
	
	private TextView textViewCommissionHelp;
	private Button buttonOk;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 
		View view = inflater.inflate(R.layout.fragment_mgmt_commission_help, container);
		
		textViewCommissionHelp = (TextView)view.findViewById(R.id.textViewCommissionHelp);
		textViewCommissionHelp.setText(Html.fromHtml(getString(R.string.help_mgmt_commission)));
		getDialog().setTitle("עזרה");
		
		buttonOk =  (Button) view.findViewById(R.id.buttonOk);
		
		buttonOk.setOnClickListener(this);
		
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// 
		this.dismiss();
		
	}

}
