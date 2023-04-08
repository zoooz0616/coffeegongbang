package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication2.R.layout.list_item;

public class hollys_list extends AppCompatActivity {
    private ListView list;
    String[] name_list = new String[91];
    String[] price_list = new String[91];
    int [] draw = new int[91];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hollys_list);

        String tmp = makeDataString(R.raw.hollys_menu);
        makeClass(tmp);

        list = (ListView)findViewById(R.id.hollys);

        CustomList adapter = new CustomList(hollys_list.this);
        list.setAdapter(adapter);
    }

    class CustomList extends ArrayAdapter<String>{
        private final Activity context;
        public CustomList(Activity context){
            super(context, R.layout.list_item,name_list);
            this.context = context;
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(list_item, null,true);
            ImageView image = (ImageView) rowView.findViewById(R.id.image);
            TextView name = (TextView) rowView.findViewById(R.id.name);
            TextView price = (TextView) rowView.findViewById(R.id.price);

            image.setImageResource(draw[position]);
            name.setText(name_list[position]);
            price.setText(price_list[position]);

            return rowView;
        }

    }

    public String makeDataString(int menu){
        InputStream inputStream = getResources().openRawResource(Integer.parseInt(String.valueOf(menu)));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i=0;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tmp = byteArrayOutputStream.toString();

        return tmp;
    }

    public void makeClass(String tmp){
        String[] arr_t;
        arr_t = tmp.split("\n");

        int count=0;
        int n=0;
        while(n<(arr_t.length)){
            if(n>=1001) break;
            name_list[count] = arr_t[n+1];
            price_list[count] = arr_t[n+4];
            String a = "hollys"+count;
            int res = getResources().getIdentifier(a, "drawable", getPackageName());
            draw[count] = res;
            n += 11;
            count++;
        }
    }
}

