package com.example.android.easylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addgoal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoal);


        Button mais = (Button) findViewById(R.id.calculate);
        mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), goal.class);
                TextView what = (TextView) findViewById(R.id.what);
                String message = what.getText().toString();
                EditText howlong = (EditText) findViewById(R.id.howlong);
                String messagenote = howlong.getText().toString();
                EditText howmuch = (EditText) findViewById(R.id.price);
                String messagenotes = howmuch.getText().toString();

                intent.putExtra("message",message);
                intent.putExtra("messagenote",messagenote);
                intent.putExtra("messagenotes",messagenotes);

                startActivity(intent);
            }
        });
    }
}
