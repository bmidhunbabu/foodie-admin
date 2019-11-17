package com.example.foodieadmin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    ListView catlist;
    List<String> ListElementsArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        catlist=(ListView)findViewById(R.id.catList);
        ListElementsArrayList = new ArrayList<String>();
        addItems();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        catlist.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CategoryFormActivity.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void addItems()
    {
        final SQLiteDatabase db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT * FROM categories", null);
        if (c.moveToFirst()) {
            do {
                ListElementsArrayList.add(c.getString(1));
            }while (c.moveToNext());

            //Toast.makeText(getApplicationContext(),c.getString(1),Toast.LENGTH_SHORT).show();
        }
        c.close();
    }

    /*public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }*/

    public void showCategories(View view) {
        Intent intent = new Intent(getApplicationContext(),CategoryActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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

}
