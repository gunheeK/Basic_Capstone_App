package com.peachjelly99.tuto1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlantListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        listView = (ListView) findViewById(R.id.listview);

        dataSetting();

        Button main = (Button)findViewById(R.id.BackButton2);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void dataSetting() {
        listAdapter listA = new listAdapter();

        for (int i=1; i<10; i++) {
            listA.addItem("name_" + i, "date_" + i, "renewal_" + i);
        }

        listView.setAdapter(listA);

    }

    public class listAdapter extends BaseAdapter {

        private ArrayList<MyItem> mItems = new ArrayList<>();

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public MyItem getItem(int i) {
            return mItems.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Context context = parent.getContext();

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item, parent, false);
            }

            TextView plant_name = (TextView) convertView.findViewById(R.id.plant_name) ;
            TextView plant_date = (TextView) convertView.findViewById(R.id.plant_date) ;
            TextView plant_renewal = (TextView) convertView.findViewById(R.id.plant_renewal) ;

            MyItem myItem = getItem(position);

            plant_name.setText(myItem.getName());
            plant_date.setText(myItem.getDate());
            plant_renewal.setText(myItem.getRenewal());


            return convertView;
        }

        public void addItem(String name, String date, String renewal) {

            MyItem mItem = new MyItem();

            mItem.setName(name);
            mItem.setDate(date);
            mItem.setRenewal(renewal);

            mItems.add(mItem);

        }
    }

    public class MyItem {

        private String name;
        private String date;
        private String renewal;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getRenewal() {
            return renewal;
        }

        public void setRenewal(String renewal) {
            this.renewal = renewal;
        }

    }
}