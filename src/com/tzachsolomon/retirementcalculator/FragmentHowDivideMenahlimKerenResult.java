package com.tzachsolomon.retirementcalculator;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockDialogFragment;


public class FragmentHowDivideMenahlimKerenResult  extends SherlockDialogFragment {
	
	
	
	private ArrayList<CalcBundle> mArrayListCalcBundle;
	private ClassDivideMenahalimKerenAdapter mCalcsAdapter;
	private ListView listViewResult;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 
		super.onCreate(savedInstanceState);
		 mArrayListCalcBundle =  (ArrayList<CalcBundle>) getArguments().getSerializable("ser");
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// 
		View view = inflater.inflate(R.layout.fragment_how_divide_keren_menahlim_result, null);
		
		getDialog().setTitle("תוצאות חישוב");
		
		initializeVariables(view);
		
		return view;
	}


	private void initializeVariables(View view) {
		// 
		listViewResult = (ListView)view.findViewById(R.id.listViewResult);
		
	}
	
	@Override
	public void onResume() {
		// 
		super.onResume();
		updateAdapterList();
	}

	private void updateAdapterList() {
		// 
		mCalcsAdapter = new ClassDivideMenahalimKerenAdapter(getSherlockActivity(), mArrayListCalcBundle);
		
		
		if (listViewResult!=null){
			listViewResult.setAdapter(mCalcsAdapter);
		}
		
	}
	
	

}
