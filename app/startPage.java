package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

class coffeeInformation implements Serializable {
    String number;
    String name ;
    String explanation;
    String size;
    String price;
    String calorie;
    String caffine;
    String sweet;
    String saturation;
    String protein;
    public String type;
    String cafeName;

    public coffeeInformation(String a, String b, String c, String d, String e, String f, String g, String h, String i, String j, String k,String l) {
        number = a;
        name = b;
        explanation = c;
        size = d;
        price = e;
        calorie = f;
        caffine = g;
        sweet = h;
        saturation = i;
        protein = j;
        type = k;
        cafeName = l;
    }
}

public class startPage extends AppCompatActivity {
    Button startTest;
    Button skipTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        startTest = (Button) findViewById(R.id.startTest);
        skipTest = (Button) findViewById(R.id.skipTest);

        startTest.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),selectCafe.class);
                startActivity(intent);
            }
        });

        skipTest.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(),mainPage.class);
                intent.putExtra("skip", false);
                startActivity(intent);
            }
        });

    }

}