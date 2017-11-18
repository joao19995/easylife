package com.example.android.easylife;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.renderer.scatter.SquareShapeRenderer;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class chart extends AppCompatActivity {
    static float[] perc  = new float[9];
    static String[] name  = new String[9];
    float positivo, negativo,balance;

    public static final int[] mycolors = {
            Color.rgb(230, 25, 175), Color.rgb(60, 180, 75), Color.rgb(255, 255, 25),
            Color.rgb(0, 130, 200), Color.rgb(245, 130, 48), Color.rgb(145, 30, 180),
            Color.rgb(210, 245, 60) , Color.rgb(240, 50, 230) , Color.rgb(170, 110, 40)
    };
    //dsddsdsd


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        final String message = intent.getStringExtra(maiscategory.EXTRA_MESSAGE);
        if (message != null)
            receivedata();


        setupiechart();

        Button mais = (Button) findViewById(R.id.mais);
        mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), mais.class);
                startActivity(intent);
            }
        });
        Button menos = (Button) findViewById(R.id.menos);
        menos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), menos.class);
                startActivity(intent);
            }
        });
        Button goal = (Button) findViewById(R.id.poupar);
        goal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), goal.class);
                startActivity(intent);
            }
        });


    }


    private void setupiechart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i=0 ;i < perc.length;i++){
            pieEntries.add(new PieEntry(perc[i],(name[i])));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"perc");
        dataSet.setColors(mycolors);
        dataSet.setValueTextSize(22f);
        dataSet.setAutomaticallyDisableSliceSpacing(true);
        PieData pieData = new PieData(dataSet);
        //get a chart

        PieChart chart = (PieChart)findViewById(R.id.chart);
        chart.setData(pieData);
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDrawSliceText(true);
        chart.setUsePercentValues(true);
        chart.animateY(1000);
        chart.setCenterText(String.valueOf(positivo)+"€"+"\n"+String.valueOf(negativo)+"€");
        chart.setCenterTextSize(14f);
        chart.setCenterTextColor(Color.BLUE);

        chart.invalidate();


        Button btnbalance = (Button) findViewById(R.id.balance);
        btnbalance.setText(String.valueOf(balance)+"€");



    }

    private void receivedata(){


        Intent intent = getIntent();
        final String message = intent.getStringExtra(maiscategory.EXTRA_MESSAGE);
        final String messagenote = intent.getStringExtra(maiscategory.EXTRA_NOTE);
        final String messagebtn = intent.getStringExtra(maiscategory.EXTRA_BUTTON);

        switch (messagebtn) {
            case "Salary":
                positivo=positivo+Float.parseFloat(message);


                break;
            case "Savings":
                positivo=positivo+Float.parseFloat(message);


                break;
            case "Money":
                positivo=positivo+Float.parseFloat(message);


                break;
            case "Car":
                perc[1] = Float.parseFloat(message)+perc[1];
                name[1] = messagebtn;

                break;
            case "Sports":
                perc[2] = Float.parseFloat(message);
                name[2] = messagebtn;

                break;
            case "Coffee":
                perc[3] = Float.parseFloat(message);
                name[3] = messagebtn;

                break;

            case "House":
                perc[4] = Float.parseFloat(message);
                name[4] = messagebtn;

                break;
            case "Pets":
                perc[5] = Float.parseFloat(message);
                name[5] = messagebtn;

                break;
            case "Clothes":
                perc[6] = Float.parseFloat(message);
                name[6] = messagebtn;

                break;
            case "Transports":
                perc[7] = Float.parseFloat(message);
                name[7] = messagebtn;

                break;
            case "Health":
                perc[8] = Float.parseFloat(message);
                name[8] = messagebtn;

                break;
            case "Food":
                perc[0] = Float.parseFloat(message);
                name[0] = messagebtn;

                break;
        }



        for (int i=0 ; i <9 ; i++){
            negativo=negativo+perc[i];

        }
        balance=positivo-negativo;








    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putFloatArray("perc", perc);
        savedInstanceState.putStringArray("name", name);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        perc = savedInstanceState.getFloatArray("perc");
        name = savedInstanceState.getStringArray("name");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.dds
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==android.R.id.home)
        finish();

        return super.onOptionsItemSelected(item);
    }


}

