package com.dealerhondabali.astraecatalog.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;


import com.dealerhondabali.astraecatalog.R;
import com.dealerhondabali.astraecatalog.persistence.Motor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Motor> listMotor;
    private LayoutInflater mLayoutInflater;

    public CustomExpandableListAdapter(Context context, ArrayList<Motor> listMotor) {
        mContext = context;
        this.listMotor = listMotor;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return listMotor.get(listPosition).arrayMotor[expandedListPosition];
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
            .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return listMotor.get(listPosition).arrayMotor.length;
    }

    @Override
    public String getGroup(int listPosition) {
        return listMotor.get(listPosition).kategoriMotor;
    }

    @Override
    public int getGroupCount() {
        return listMotor.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
            .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
