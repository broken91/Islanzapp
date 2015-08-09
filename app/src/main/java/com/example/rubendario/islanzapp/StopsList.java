package com.example.rubendario.islanzapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RubenDario on 8/9/2015.
 */
public class StopsList extends ActionBarActivity {

    String[] stopsName = new String[32];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stops_list);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        getSupportActionBar().setTitle(getResources().getString(R.string.stops));

        stopsName = getResources().getStringArray(R.array.stops_30);
        ArrayList<String> stops = new ArrayList<String>();
        for (int i=0; i<stopsName.length; i++ )
            stops.add(i, stopsName[i]);

        ListView lv = (ListView) findViewById(R.id.list2);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.stops_list_item, stops));

        String[] array = getResources().getStringArray(R.array.stops_30);
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(array);
        ArrayList<String> arrayList = new ArrayList<String>(list);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {

            }
        });
    }
}
