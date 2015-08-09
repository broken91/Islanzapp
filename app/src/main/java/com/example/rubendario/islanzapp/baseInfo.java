package com.example.rubendario.islanzapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by RubenDario on 8/7/2015.
 */
public class baseInfo extends ActionBarActivity {

    private TextView info;
    private int infoInt;
    private int titleInt;
    private int res;
    private String[] aux1 = new String[7];
    private String[] aux2 = new String[1];
    private String[] aux3 = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_info_layout);

        info = (TextView) findViewById(R.id.infoContent);
        Intent i = getIntent();
        titleInt = i.getIntExtra("Int_title", -1);
        infoInt = i.getIntExtra("Int_info", -1);

        switch (titleInt){
            case 0:
                aux1 = getResources().getStringArray(R.array.child_1);
                getSupportActionBar().setTitle(aux1[infoInt]);
                switch (infoInt){
                    case 0:
                        res = R.raw.charco_del_palo;
                        break;
                    case 1:
                        res = R.raw.costa_teguise;
                        break;
                    case 2:
                        res = R.raw.faro_de_punta_pechiguera;
                        break;
                    case 3:
                        res = R.raw.playa_blanca;
                        break;
                    case 4:
                        res = R.raw.puerto_calero;
                        break;
                    case 5:
                        res = R.raw.puerto_del_carmen;
                        break;
                    case 6:
                        res = R.raw.teguise;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), infoInt, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            case 1:
                aux2 = getResources().getStringArray(R.array.child_2);
                getSupportActionBar().setTitle(aux2[infoInt]);
                switch (infoInt){
                    case 0:
                        res = R.raw.charco_del_palo;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), infoInt, Toast.LENGTH_SHORT).show();
                        break;
                }
            break;
            case 2:
                aux3 = getResources().getStringArray(R.array.child_3);
                getSupportActionBar().setTitle(aux3[infoInt]);
                switch (infoInt){
                    case 0:
                        res = R.raw.hotel_1;
                        break;
                    case 1:
                        res = R.raw.hotel_2;
                        break;
                    case 2:
                        res = R.raw.hotel_3;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), infoInt, Toast.LENGTH_SHORT).show();
                        break;
                }
                break;
            default:
                Toast.makeText(getApplicationContext(), titleInt, Toast.LENGTH_SHORT).show();
                break;
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_launcher);
        //getSupportActionBar().setTitle(aux[titleInt]);


        try {
            createText(res);
        } catch (IOException e){
            Toast.makeText(getApplicationContext(),
                    "Problems: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        info.setMovementMethod(new ScrollingMovementMethod());

    }

    public void createText(int name) throws IOException {
        String str="";
        StringBuffer buf = new StringBuffer();
        InputStream is = this.getResources().openRawResource(name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is!=null) {
            while ((str = reader.readLine()) != null) {
                buf.append(str + "\n" );
            }
        }
        is.close();
    info.setText(buf.toString());
    }

}
