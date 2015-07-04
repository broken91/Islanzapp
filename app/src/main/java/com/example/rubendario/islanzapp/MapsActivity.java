package com.example.rubendario.islanzapp;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

public class MapsActivity extends ActionBarActivity {

    private GoogleMap mMap;
    android.support.v7.app.ActionBar bar;// Might be null if Google Play services APK is not available.
//Hola
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        bar = this.getSupportActionBar();
        initToolbars();
   //     bar.show();
        setUpMapIfNeeded();
        //mMap.setMyLocationEnabled(true);
        onMapReady(mMap);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);
    //    return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items

        switch (item.getItemId()) {
            case R.id.search_button:
                Toast.makeText(MapsActivity.this, getString(R.string.action_search), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_moderador:
                Toast.makeText(MapsActivity.this, getString(R.string.action_moderador), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_Bitacora:
                Toast.makeText(MapsActivity.this, getString(R.string.action_Bitacora), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                PopupMenu popup = new PopupMenu(bar.getThemedContext(), findViewById(R.id.action_settings));
                popup.getMenuInflater()
                        .inflate(R.menu.pop_up_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(
                                MapsActivity.this,
                                item.getTitle(),
                                Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show(); //showing popup menu*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    //@Override
    public void onMapReady(GoogleMap map) {
        LatLng lanzarote = new LatLng(29.0386277,-13.6504782);
        map.clear();
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(lanzarote, 9));

        map.addMarker(new MarkerOptions()
                .title("Lanzarote")
                .snippet(getString(R.string.message))
                .position(lanzarote));
    }

    //Probando Metodo
    private void initToolbars() {
       // Toolbar toolbarTop = (Toolbar) findViewById(R.id.toolbar_top);
       //setSupportActionBar(toolbarTop);

        Toolbar toolbarBottom = (Toolbar) findViewById(R.id.toolbar_bottom);
        toolbarBottom.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_like:
                        Toast.makeText(MapsActivity.this, getString(R.string.action_like), Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_rate:
                        Toast.makeText(MapsActivity.this, getString(R.string.action_rate), Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.menu_people:
                        Toast.makeText(MapsActivity.this, getString(R.string.action_group), Toast.LENGTH_SHORT).show();
                        return true;
                }
                return true;
            }
        });
        // Inflate a menu to be displayed in the toolbar
        toolbarBottom.inflateMenu(R.menu.botton_toolbar);
    }
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }
}
