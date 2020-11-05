package com.example.lenovo.expandablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    List<String> listview;
    HashMap<String,List<String>> listchild;
    CustomAddapter customAddapter;
    int lastExpandpst=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareListData();
        expandableListView=findViewById(R.id.el);
      customAddapter =new CustomAddapter(this,listview,listchild);
      expandableListView.setAdapter(customAddapter);

      expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
          @Override
          public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

              String grpna=listview.get(i);
              Toast.makeText(getApplicationContext(),"group name: "+grpna,Toast.LENGTH_SHORT).show();
              return false;
          }
      });
      expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
          @Override
          public void onGroupCollapse(int i) {
              String grpna=listview.get(i);
              Toast.makeText(getApplicationContext(),grpna+" is collapsed",Toast.LENGTH_SHORT).show();
          }
      });
      expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
          @Override
          public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

              String child=listchild.get(listview.get(i)).get(i1);
              Toast.makeText(getApplicationContext(),child,Toast.LENGTH_SHORT).show();
              return false;
          }
      });
      expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
          @Override
          public void onGroupExpand(int i) {
              if(lastExpandpst !=-1 && lastExpandpst !=i)
              {
                  expandableListView.collapseGroup(lastExpandpst);
              }
              lastExpandpst =i;
          }
      });

    }

    void prepareListData()
    {
        String[] header=getResources().getStringArray(R.array.hdd);
        String[] dd=getResources().getStringArray(R.array.hdd1);

        listview=new ArrayList<>();
        listchild=new HashMap<>();

        for(int i=0;i<header.length;i++)
        {
            listview.add(header[i]);
            List<String> answer=new ArrayList<>();
            answer.add(dd[i]);

            listchild.put(listview.get(i),answer);
        }
    }
}
