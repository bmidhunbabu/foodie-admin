package com.example.foodieadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLiteDatabase db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS categories(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,parent_id INTEGER DEFAULT NULL,created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");
        db.execSQL("CREATE TABLE IF NOT EXISTS foods(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,description VARCHAR,price INTEGER,category_id INTEGER DEFAULT NULL,created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");
        db.execSQL("CREATE TABLE IF NOT EXISTS menu(id INTEGER PRIMARY KEY AUTOINCREMENT,food_id INTEGER,created_at DATETIME DEFAULT CURRENT_TIMESTAMP);");
        db.close();
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
