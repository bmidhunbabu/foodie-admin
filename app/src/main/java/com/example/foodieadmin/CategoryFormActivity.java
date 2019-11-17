package com.example.foodieadmin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextPaint;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CategoryFormActivity extends AppCompatActivity {
    List<String> labels = new ArrayList<String>();
    String[] arraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_form);

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

        //db = new DatabaseHelper(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        labels.add("Select Parent Category");
        addCategories();
        Spinner catSpinner = (Spinner) findViewById(R.id.spn_parent);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);
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

    /*int i=1;

    Cursor c=db.rawQuery("SELECT * FROM categories", null);
    while (c.moveToNext())
    {
        arraySpinner[i]=c.getString(1);
        i++;
    }
    c.close();*/
}
    public void addCategory(View view) {
        EditText edtName = findViewById(R.id.edt_name);
        Spinner spinner=(Spinner)findViewById(R.id.spn_parent);
        //long id = db.insertCategory(edtName.getText().toString());
        //Toast.makeText(getApplicationContext(), (int) id,Toast.LENGTH_SHORT)
          //  .show();
        String parentid="";
        final SQLiteDatabase db = openOrCreateDatabase("FoodieAdmin", getApplicationContext().MODE_PRIVATE, null);
        if(spinner.getSelectedItem().toString().equals("Select Parent Category"))
        {
            db.execSQL("insert into categories (name) values ('" + edtName.getText() + "');");
             }
        else {
            Cursor c=db.rawQuery("Select * from categories where name='"+spinner.getSelectedItem().toString()+"'",null);
            if(c.moveToFirst())
            {
                parentid=c.getString(0);
            }
           // Toast.makeText(getApplicationContext(),parentid,Toast.LENGTH_SHORT).show();
           int pid=Integer.parseInt(parentid);
            c.close();
            //Toast.makeText(getApplicationContext(),"insert into categories (name,parent_id) values ('"+edtName.getText()+"',"+pid+");",Toast.LENGTH_SHORT).show();
            db.execSQL("insert into categories (name,parent_id) values ('"+edtName.getText()+"',"+pid+");");
            db.close();
        }
            Toast.makeText(getApplicationContext(),"Category added",Toast.LENGTH_SHORT);
            Intent intent = new Intent(getApplicationContext(),Category.class);
            startActivity(intent);
    }

}
