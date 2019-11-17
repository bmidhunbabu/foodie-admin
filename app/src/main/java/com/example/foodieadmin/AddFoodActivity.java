package com.example.foodieadmin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddFoodActivity extends AppCompatActivity {

    List<String> labels = new ArrayList<String>();
    String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        labels.add("Select Parent Category");
        addCategories();
        Spinner catSpinner = (Spinner) findViewById(R.id.categories);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);
    }

    public void showCategories(View view) {
        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Categories",Toast.LENGTH_SHORT).show();
    }

    public void showFoods(View view) {
        Intent intent = new Intent(getApplicationContext(),FoodActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Foods",Toast.LENGTH_SHORT).show();
    }

    public void showMenu(View view) {
        Intent intent = new Intent(getApplicationContext(),DailyMenuActivity.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"Menu",Toast.LENGTH_SHORT).show();
    }

    public void showOrders(View view) {
        Toast.makeText(getApplicationContext(),"Orders",Toast.LENGTH_SHORT).show();
    }

    public void addCategories()
    {
        // Select All Query
        String selectQuery = "SELECT  * FROM categories" ;
        final SQLiteDatabase db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
    }

    public void addFood(View view) {
        EditText name = findViewById(R.id.foodname);
        EditText description = findViewById(R.id.description);
        EditText price = findViewById(R.id.price);
        Spinner category = findViewById(R.id.categories);
        int categoryId;
        int intPrice = Integer.parseInt(price.getText().toString());
        final SQLiteDatabase db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        Cursor c = db.rawQuery("Select * from categories where name='"+ category.getSelectedItem().toString() +"'",null);
        if(c.moveToFirst())
        {
            categoryId = Integer.parseInt(c.getString(0));
            // Toast.makeText(getApplicationContext(),parentid,Toast.LENGTH_SHORT).show();
            c.close();
            String query = "insert into foods (name,description,price,category_id) values ('"+name.getText()+"','"+description.getText().toString()+"',"+ intPrice +","+ categoryId +");";
            description.setText(query);
            db.execSQL(query);
            db.close();
            Toast.makeText(getApplicationContext(),"Food item added",Toast.LENGTH_SHORT);
            Intent intent = new Intent(getApplicationContext(),FoodActivity.class);
            startActivity(intent);
        }
    }
}
