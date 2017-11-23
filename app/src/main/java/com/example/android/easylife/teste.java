package com.example.android.easylife;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.easylife.data.PetDbHelper;
import com.example.android.easylife.data.PetContract.PetEntry;

public class teste extends AppCompatActivity {

    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        mDbHelper = new PetDbHelper(this);

        displayDatabaseInfo();

    }





    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PetEntry._ID,
                PetEntry.COLUMN_MONEY,
                PetEntry.COLUMN_NOTE,
                PetEntry.COLUMN_CATEGORY,
                PetEntry.COLUMN_DATE };




        // Perform a query on the pets table
        Cursor cursor = db.query(
                PetEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                PetEntry._ID + ">?",                  // The columns for the WHERE clause
                new String[] {"0"},                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                PetEntry._ID + " ASC");                   // The sort order

        TextView displayView = (TextView) findViewById(R.id.text_view_pet);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(PetEntry._ID + " - " +
                    PetEntry.COLUMN_MONEY + " - " +
                    PetEntry.COLUMN_NOTE + " - " +
                    PetEntry.COLUMN_CATEGORY + " - " +
                    PetEntry.COLUMN_DATE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_MONEY);
            int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_NOTE);
            int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_CATEGORY);
            int weightColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_DATE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                String currentGender = cursor.getString(genderColumnIndex);
                String currentWeight = cursor.getString(weightColumnIndex);
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
