package com.example.android.easylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class maiscategory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String EXTRA_NOTE = "com.example.myfirstapp.NOTE";
    public static final String EXTRA_BUTTON = "com.example.myfirstapp.BUTTON";

    private int[] more = {R.id.mmoney, R.id.msalary, R.id.msavings,};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maiscategory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        final String message = intent.getStringExtra(mais.EXTRA_MESSAGE);
        final String messagenote = intent.getStringExtra(mais.EXTRA_NOTE);

        /* Capture the layout's TextView and set the string as its text */
        TextView textView = (TextView) findViewById(R.id.result);
        textView.setText(message);
        EditText editText = (EditText) findViewById(R.id.note);
        editText.setText(messagenote);

        handlebutton();

    }
    /*private void handlebutton() {

        final Button button = (Button) findViewById(R.id.mmoney);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
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
        });
    }*/
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
        for (int id : more) {

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
}

