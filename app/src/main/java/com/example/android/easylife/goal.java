package com.example.android.easylife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class goal extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Intent intent = getIntent();

        final String what = intent.getStringExtra("message");
        final String howlong = intent.getStringExtra("messagenote");
        final String price = intent.getStringExtra("messagenotes");

        if (what != null) {


        /* Capture the layout's TextView and set the string as its text */
            TextView textView = (TextView) findViewById(R.id.test);
            textView.setText(what);
            TextView textView1 = (TextView) findViewById(R.id.test2);
            textView1.setText(howlong);
            TextView textView2 = (TextView) findViewById(R.id.test3);
            textView2.setText(price);
        }


        Button mais = (Button) findViewById(R.id.buttontest);
        mais.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), addgoal.class);


                startActivity(intent);
            }
        });
    }
}
