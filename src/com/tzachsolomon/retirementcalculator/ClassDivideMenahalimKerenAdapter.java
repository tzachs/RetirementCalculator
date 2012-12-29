package com.tzachsolomon.retirementcalculator;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class ClassDivideMenahalimKerenAdapter extends BaseAdapter {

	private ArrayList<CalcBundle> mItems;
	private LayoutInflater mInflater;

	public ClassDivideMenahalimKerenAdapter (SherlockFragmentActivity sherlockFragmentActivity,
			ArrayList<CalcBundle> items){
		mItems = items;
		mInflater = (LayoutInflater)sherlockFragmentActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	@Override
	public int getCount() {
		// 
		return mItems.size();
	}

	@Override
	public Object getItem(int position) {
		// 
		return position;
	}

	@Override
	public long getItemId(int position) {
		// 
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 
		View ret = convertView;
		if ( ret == null){
				ret = mInflater.inflate(R.layout.calc_bundle_list_row, null);
		}
		TextView textViewPercentKeren = (TextView)ret.findViewById(R.id.textViewPercentKeren);
		TextView textViewPercentMenahalim = (TextView)ret.findViewById(R.id.textViewPercentMenahalim);
		TextView textViewPensionSalary = (TextView)ret.findViewById(R.id.textViewPensionSalary);
		TextView textViewTotal = (TextView)ret.findViewById(R.id.textViewTotal);
		
		textViewPercentKeren.setText(mItems.get(position).toStringPercentKeren());
		textViewPercentMenahalim.setText(mItems.get(position).toStringPercentMenahalim());
		textViewPensionSalary.setText(mItems.get(position).toStringPensionSalary());
		textViewTotal.setText(mItems.get(position).toStringTotal());
		
		
		return ret;
	}

}
