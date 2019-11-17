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

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    ListView list;
    List<String> titleList;
    List<String> descriptionList;
    List<String> priceList;
    AdvancedListAdapter adapter;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddFoodActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(),"Add Food",Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linearLayout = findViewById(R.id.linearLayout);

        titleList = new ArrayList<String>();
        descriptionList = new ArrayList<String>();
        priceList = new ArrayList<String>();

        db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        Cursor c=db.rawQuery("SELECT * FROM foods", null);
        if (c.moveToFirst()) {
            do {
                titleList.add(c.getString(1));
                descriptionList.add(c.getString(2));
                priceList.add("Rs. " + c.getString(3));
            } while (c.moveToNext());
            //Toast.makeText(getApplicationContext(),c.getString(1),Toast.LENGTH_SHORT).show();
        }
        c.close();
        adapter = new AdvancedListAdapter(this, titleList, descriptionList,priceList);
        list=(ListView)findViewById(R.id.foodList);

        list.setAdapter(adapter);

        registerForContextMenu(list);
    }

    /*public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }*/

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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_food, menu);
        //menu.setHeaderTitle("Select The Action");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
            //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
              //      .getMenuInfo();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Adapter adapter = getListAdapter();
        Object obj = adapter.getItem(info.position);
        if(item.getItemId()==R.id.addToMenu) {
            addToMenu(obj.toString());
            Toast.makeText(getApplicationContext(),obj.toString(),Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"add to menu",Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId()==R.id.deleteFood){
            Toast.makeText(getApplicationContext(),"deleted food",Toast.LENGTH_LONG).show();
        } else {
            return false;
        }
        return true;
    }

    public void addToMenu(String foodName) {
        int foodId;
        Cursor c=db.rawQuery("Select * from foods where name='"+ foodName +"'",null);
        if(c.moveToFirst())
        {
            foodId = Integer.parseInt(c.getString(0));
            c.close();
            db.execSQL("insert into menu (food_id) values ("+ foodId +");");
            Toast.makeText(getApplicationContext(),"added to menu",Toast.LENGTH_SHORT).show();
        }
    }
}
