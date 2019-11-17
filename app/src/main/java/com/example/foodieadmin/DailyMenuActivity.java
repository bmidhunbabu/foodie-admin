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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DailyMenuActivity extends AppCompatActivity {

    ListView list;
    List<String> titleList;
    ArrayAdapter<String> adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_menu);
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

        db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        titleList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
        Cursor c=db.rawQuery("SELECT * FROM foods inner join menu where foods.id = menu.id", null);
        if (c.moveToFirst()) {
            do {
                titleList.add(c.getString(1));
            } while (c.moveToNext());
            //Toast.makeText(getApplicationContext(),c.getString(1),Toast.LENGTH_SHORT).show();
        }
        c.close();

        list = findViewById(R.id.menuList);
        list.setAdapter(adapter);
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
}
