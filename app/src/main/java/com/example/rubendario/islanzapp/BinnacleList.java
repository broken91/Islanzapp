package com.example.rubendario.islanzapp;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by RubenDario on 7/25/2015.
 */
public class BinnacleList extends ActionBarActivity{

    private String[] headers = new String[3];
    private String[][] children = new String[3][3];

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


// initiate the listadapter


    }


    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String>[] childList = new ArrayList[3];

        headers = this.getResources().getStringArray(R.array.header_items);
        children[0] = this.getResources().getStringArray(R.array.child_museums);
        children[1] = this.getResources().getStringArray(R.array.child_beaches);
        children[2] = this.getResources().getStringArray(R.array.child_places);

        for (int i=0; i<headers.length; i++) {
            listDataHeader.add(i, headers[i]);
            childList[i] = new ArrayList<String>();

            for (int j=0; j<children[i].length; j++)
                childList[i].add(j, children[i][j]);

            listDataChild.put(listDataHeader.get(i), childList[i]);
        }


    }

}
