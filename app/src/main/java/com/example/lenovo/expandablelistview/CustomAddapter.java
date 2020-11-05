package com.example.lenovo.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAddapter extends BaseExpandableListAdapter {
    Context context;
    List<String> listview;
    HashMap<String,List<String>> listchild;

    public CustomAddapter(Context context, List<String> listview, HashMap<String, List<String>> listchild) {
        this.context = context;
        this.listview = listview;
        this.listchild = listchild;
    }

    @Override
    public int getGroupCount() {


        return listview.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listchild.get(listview.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listview.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listchild.get(listview.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String headertext= (String) getGroup(i);

        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.group_layout,null);
        }
       TextView textView= view.findViewById(R.id.grp);
        textView.setText(headertext);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String childtext= (String) getChild(i,i1);

        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.child_layout,null);
        }
        TextView textView= view.findViewById(R.id.cd);
        textView.setText(childtext);

        return view;

    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
