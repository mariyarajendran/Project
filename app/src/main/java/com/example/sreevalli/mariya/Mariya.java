package com.example.sreevalli.mariya;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sreevalli.mariya.Sqlite.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mariya extends AppCompatActivity {

    private List<Mypojo> mypojos = new ArrayList<>();
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private ListView listView;
    ListAdapter listAdapter;
    Helper helper;

    ArrayList<String> name=new ArrayList<String>();
    ArrayList<String> area=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mariya);

       // recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        listView=(ListView)findViewById(R.id.list);

        helper=new Helper(this);

       /* RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/
        datas();
        loaddatasqlite();


    }

    public void datas(){

      /*  Mypojo mypojo=new Mypojo("Mariya","Chennai");
        mypojos.add(mypojo);

        mypojo = new Mypojo("Kumar","Kolathur");
        mypojos.add(mypojo);

        mypojo = new Mypojo("Sree","Rajapalayam");
        mypojos.add(mypojo);*/


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://legalnote.org//hotelrsbhavaan/indiangravy.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(getApplication(),""+response,Toast.LENGTH_SHORT).show();
                        try {
                            //converting the string to json array object
                            JSONObject jsonObject=new JSONObject(response);

                            //JSONArray array = new JSONArray(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("result");

                            //traversing through all the object
                            for (int i = 0; i < jsonArray.length(); i++) {

                                //getting product object from json array
                                JSONObject product = jsonArray.getJSONObject(i);

                                helper.insert(product.getString("title"),product.getString("description"));

                                //adding the product to product list
                            /*    mypojos.add(new Mypojo(
                                        product.getString("title"),
                                        product.getString("description")

                                ));*/


                            }

                            //creating adapter object and setting it to recyclerview
                           // mAdapter = new MyAdapter(mypojos);
                            loaddatasqlite();
                            Toast.makeText(getApplicationContext(),""+helper.numberOfRows(),Toast.LENGTH_SHORT).show();

                            //recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }


    public void loaddatasqlite(){

        Cursor cursor=helper.display();
        if (cursor.moveToFirst()) {
            do {

                name.add(cursor.getString(1));
                area.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }

        listAdapter=new ListAdapter(this,name,area);
        listView.setAdapter(listAdapter);
    }




}
