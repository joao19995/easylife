package com.example.android.easylife;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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

import java.util.Calendar;

public class menoscategory extends AppCompatActivity {
    int year_x, mouth_x, day_x;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_NOTE = "com.example.myfirstapp.NOTE";
    public static final String EXTRA_BUTTON = "com.example.myfirstapp.BUTTON";
    private int[] less = {R.id.car, R.id.sports, R.id.coffee,R.id.house, R.id.pets, R.id.clothes,R.id.transports,R.id.health, R.id.food};
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menoscategory);
        final Calendar cal = Calendar.getInstance();
        year_x= cal.get(Calendar.YEAR);
        mouth_x= cal.get(Calendar.MONTH);
        day_x= cal.get(Calendar.DAY_OF_MONTH);
        pickdate();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        final String message = intent.getStringExtra(menos.EXTRA_MESSAGE);
        final String messagenote = intent.getStringExtra(menos.EXTRA_NOTE);

        /* Capture the layout's TextView and set the string as its text */
        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText(message);
        EditText editText = (EditText) findViewById(R.id.note);
        editText.setText(messagenote);
        handlebutton();
    }

    private void handlebutton(){

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Just append/set the text of clicked button
                Button button = (Button) v;
                Intent intent = new Intent(getApplicationContext(), chart.class);
                TextView result = (TextView) findViewById(R.id.result);
                String message = result.getText().toString();
                EditText note = (EditText) findViewById(R.id.note);
                String messagenote = note.getText().toString();
                String messagebtn = button.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(EXTRA_NOTE, messagenote);
                intent.putExtra(EXTRA_BUTTON, messagebtn);


                startActivity(intent);

            }
        };
        for (int id : less) {

            findViewById(id).setOnClickListener(listener);
        }

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
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }



    public void pickdate(){

        Button btndate=(Button)findViewById(R.id.date);
        btndate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(DIALOG_ID);
                    }
                }
        );

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id==DIALOG_ID)
            return new DatePickerDialog(this,dplistner,year_x,mouth_x,day_x);
        return super.onCreateDialog(id);
    }
    private DatePickerDialog.OnDateSetListener dplistner = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            year_x=i;
            mouth_x=i1+1;
            day_x=i2;
            Toast.makeText(menoscategory.this,year_x+"/"+mouth_x+"/"+day_x,Toast.LENGTH_LONG).show();



        }
    };
}
