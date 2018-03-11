package com.example.sreevalli.mariya;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import java.util.List;

/**
 * Created by sreevalli on 3/10/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.DemoAdapter> {


    private List<Mypojo> mypojos;

    public class DemoAdapter extends RecyclerView.ViewHolder {
        public TextView name, area;

        public DemoAdapter(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            area = (TextView) view.findViewById(R.id.area);

        }
    }


    public MyAdapter(List<Mypojo> mypojos){

        this.mypojos=mypojos;
    }



    @Override
    public DemoAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);

        return new DemoAdapter(itemView);
    }

    @Override
    public void onBindViewHolder(DemoAdapter holder, int position) {

        Mypojo mypojo = mypojos.get(position);
        holder.name.setText(mypojo.getName());
        holder.area.setText(mypojo.getArea());

    }



    @Override
    public int getItemCount() {
        return mypojos.size();
    }
}
