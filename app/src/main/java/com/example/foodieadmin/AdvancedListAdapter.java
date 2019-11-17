package com.example.foodieadmin;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdvancedListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private List<String> maintitle;
    private List<String> description;
    private List<String> price;
    //private final Integer[] imgid;

    public AdvancedListAdapter(Activity context, List<String> maintitle, List<String> description, List<String> price) {
        super(context, R.layout.advanced_list, maintitle);
        this.context=context;
        this.maintitle=maintitle;
        this.description =description;
        this.price=price;
        //this.imgid=imgid;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.advanced_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        TextView priceText = (TextView) rowView.findViewById(R.id.price);

        titleText.setText(maintitle.get(position));
        //imageView.setImageResource(imgid[position]);
        subtitleText.setText(description.get(position));
        priceText.setText(price.get(position));

        return rowView;
    }
}
