package com.example.rubendario.islanzapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RubenDario on 8/4/2015.
 */
public class SearchableActivity extends ActionBarActivity implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener {

    private ListView myList;
    private SearchView searchView;
    private SearchHelper mDbHelper;
    private MyCustomAdapter defaultAdapter;
    private ArrayList<String> nameList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.buscar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setContentView(R.layout.activity_search);


        nameList = new ArrayList<String>();
        //
        List<String>[] childList = new ArrayList[3];
        String[] headersS = this.getResources().getStringArray(R.array.header_items);
        String[][] childrenS = new String[3][3];
        childrenS[0] = this.getResources().getStringArray(R.array.child_1);
        childrenS[1] = this.getResources().getStringArray(R.array.child_2);
        childrenS[2] = this.getResources().getStringArray(R.array.child_3);

        /*for (int i=0; i<headersS.length; i++) {
            nameList.add(i, headersS[i]);
            childList[i] = new ArrayList<String>();

            for (int j=0; j<childrenS[i].length; j++)
                childList[i].add(j, childrenS[i][j]);

        }*/

        int cont = childrenS[0].length + childrenS[1].length + childrenS[2].length;
        int i=0;
        while (i<cont) {
            for (int k=0; k<headersS.length; k++){
                childList[k] = new ArrayList<String>();

                for (int j=0; j<childrenS[k].length; j++) {
                    childList[k].add(j, childrenS[k][j]);
                    nameList.add(i, childrenS[k][j]);
                    i++;
                }

            }



        }

        //



        //for simplicity we will add the same name for 20 times to populate the nameList view
        /*for (int i = 0; i < 10; i++) {
            nameList.add("Probando " + i);
        }*/

        //relate the listView from java to the one created in xml
        myList = (ListView) findViewById(R.id.list);

        //show the ListView on the screen
        // The adapter MyCustomAdapter is responsible for maintaining the data backing this nameList and for producing
        // a view to represent an item in that data set.
        defaultAdapter = new MyCustomAdapter(SearchableActivity.this, nameList);
        myList.setAdapter(defaultAdapter);

        //prepare the SearchView
        /*searchView = (android.support.v7.widget.SearchView) findViewById(R.id.search);

        //Sets the default or resting state of the search field. If true, a single search icon is shown by default and
        // expands to show the text field and other buttons when pressed. Also, if the default state is iconified, then it
        // collapses to that state when the close button is pressed. Changes to this property will take effect immediately.
        //The default value is true.
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);*/

        mDbHelper = new SearchHelper(this);
        mDbHelper.open();

        //Clear all names
        mDbHelper.deleteAllNames();

        // Create the list of names which will be displayed on search
        for (String name : nameList) {
            mDbHelper.createList(name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_search, menu);
        //MenuItem searchItem = menu.findItem(R.id.search);
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();


        //Sets the default or resting state of the search field. If true, a single search icon is shown by default and
        // expands to show the text field and other buttons when pressed. Also, if the default state is iconified, then it
        // collapses to that state when the close button is pressed. Changes to this property will take effect immediately.
        //The default value is true.
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        new SearchViewFormatter()
                //.setSearchBackGroundResource(R.color.actionbar_background)
                .setSearchIconResource(R.drawable.buscar, false, true) //true to icon inside edittext, false to outside
                //.setSearchVoiceIconResource(R.drawable.buscar)
                .setSearchTextColorResource(R.color.actionbar_text)
                //.setSearchHintColorResource(R.color.actionbar_text)
                //.setSearchCloseIconResource(R.drawable.buscar)
                .setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS)
                .format(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDbHelper  != null) {
            mDbHelper.close();
        }
    }

    @Override
    public boolean onClose() {
        myList.setAdapter(defaultAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        displayResults(query + "*");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()){
            displayResults(newText + "*");
        } else {
            myList.setAdapter(defaultAdapter);
        }

        return false;
    }

    /**
     * Method used for performing the search and displaying the results. This method is called every time a letter
     * is introduced in the search field.
     *
     * @param query Query used for performing the search
     */
    private void displayResults(String query) {

        Cursor cursor = mDbHelper.searchByInputText((query != null ? query : "@@@@"));

        if (cursor != null) {

            String[] from = new String[] {SearchHelper.COLUMN_NAME};

            // Specify the view where we want the results to go
            int[] to = new int[] {R.id.search_result_text_view};

            // Create a simple cursor adapter to keep the search data
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.result_search_item, cursor, from, to);
            myList.setAdapter(cursorAdapter);

            // Click listener for the searched item that was selected
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the cursor, positioned to the corresponding row in the result set
                    Cursor cursor = (Cursor) myList.getItemAtPosition(position);

                    // Get the state's capital from this row in the database.
                    String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(SearchableActivity.this, selectedName, Toast.LENGTH_SHORT).show();

                    // Set the default adapter
                    myList.setAdapter(defaultAdapter);

                    // Find the position for the original list by the selected name from search
                    for (int pos = 0; pos < nameList.size(); pos++) {
                        if (nameList.get(pos).equals(selectedName)){
                            position = pos;
                            break;
                        }
                    }

                    // Create a handler. This is necessary because the adapter has just been set on the list again and
                    // the list might not be finished setting the adapter by the time we perform setSelection.
                    Handler handler = new Handler();
                    final int finalPosition = position;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            myList.setSelection(finalPosition);
                        }
                    });

                    searchView.setQuery("", true);
                }
            });

        }
    }
}
