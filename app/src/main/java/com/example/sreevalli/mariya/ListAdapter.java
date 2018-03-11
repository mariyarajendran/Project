package com.example.sreevalli.mariya;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sreevalli on 3/10/2018.
 */

public class ListAdapter extends BaseAdapter{

Activity activity;
//List<Mypojo> mypojos;
    ArrayList<String> namelist;
    ArrayList<String> arealist;
private LayoutInflater inflater;
    public TextView name, area;

    public ListAdapter(Activity activity,ArrayList<String> namelist,ArrayList<String> arealist){

        this.activity=activity;
        this.namelist=namelist;
        this.arealist=arealist;
       // this.mypojos=mypojos;
    }


    @Override
    public int getCount() {
        return namelist.size();
    }

    @Override
    public Object getItem(int position) {
        return namelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list, null);

        name = (TextView) convertView.findViewById(R.id.name);
        area = (TextView) convertView.findViewById(R.id.area);

      //  Mypojo mypojo=mypojos.get(position);
        name.setText(namelist.get(position));
        area.setText(arealist.get(position));




        return convertView;
    }
}
