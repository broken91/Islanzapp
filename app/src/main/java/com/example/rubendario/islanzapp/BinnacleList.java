package com.example.rubendario.islanzapp;


import android.app.ListActivity;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by RubenDario on 7/25/2015.
 */
public class BinnacleList extends ActionBarActivity{

    private String[] headers = new String[3];
    private String[][] children = new String[3][7];

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
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle(getResources().getString(R.string.action_Bitacora));


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.list);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


// initiate the listadapter

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                int shareInt1= groupPosition;
                int shareInt2= childPosition;
                /*switch (groupPosition) {
                    case 0:
                        shareString = headers[0];
                        switch (childPosition){
                            case 0:
                                shareInt = R.raw.charco_del_palo;
                                break;
                            case 1:
                                shareInt = R.raw.costa_teguise;
                                break;
                            case 2:
                                shareInt = R.raw.faro_de_punta_pechiguera;
                                break;
                            case 3:
                                shareInt = R.raw.playa_blanca;
                                break;
                            case 4:
                                shareInt = R.raw.puerto_calero;
                                break;
                            case 5:
                                shareInt = R.raw.puerto_del_carmen;
                                break;
                            case 6:
                                shareInt = R.raw.teguise;
                                break;
                        }

                        break;
                    case 1:
                        shareString = headers[1];
                        switch (childPosition){
                            case 0:
                                break;
                        }
                        break;
                    case 2:
                        shareString = headers[2];
                        switch (childPosition){
                            case 0:
                                break;
                        }
                        break;

                }*/
                if (shareInt1!=1){
                Intent i = new Intent(getApplicationContext(), baseInfo.class);
                i.putExtra("Int_title", shareInt1);
                i.putExtra("Int_info", shareInt2);
                startActivity(i);}
                else{
                    Intent j = new Intent(getApplicationContext(), StopsList.class);
                    startActivity(j);
                }

                return true;
            }
        });


    }


    private void prepareListData(){
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        List<String>[] childList = new ArrayList[3];

        headers = this.getResources().getStringArray(R.array.header_items);
        children[0] = this.getResources().getStringArray(R.array.child_1);
        children[1] = this.getResources().getStringArray(R.array.child_2);
        children[2] = this.getResources().getStringArray(R.array.child_3);

        for (int i=0; i<headers.length; i++) {
            listDataHeader.add(i, headers[i]);
            childList[i] = new ArrayList<String>();

            for (int j=0; j<children[i].length; j++) {
                if (children[i][j] != null)
                childList[i].add(j, children[i][j]);
            }

            listDataChild.put(listDataHeader.get(i), childList[i]);
        }


    }

}
